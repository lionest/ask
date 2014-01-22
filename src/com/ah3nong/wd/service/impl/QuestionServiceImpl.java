package com.ah3nong.wd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ah3nong.wd.bean.Domain;
import com.ah3nong.wd.bean.Expert;
import com.ah3nong.wd.bean.Probing;
import com.ah3nong.wd.bean.Question;
import com.ah3nong.wd.bean.QuestionExpert;
import com.ah3nong.wd.bean.QuestionExtraContentHis;
import com.ah3nong.wd.bean.QuestionHis;
import com.ah3nong.wd.bean.QuestionImg;
import com.ah3nong.wd.bean.QuestionKeyword;
import com.ah3nong.wd.bean.Reply;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.common.Pager;
import com.ah3nong.wd.dao.DomainDao;
import com.ah3nong.wd.dao.ExpertDao;
import com.ah3nong.wd.dao.ProbingDao;
import com.ah3nong.wd.dao.QuestionDao;
import com.ah3nong.wd.dao.QuestionHisDao;
import com.ah3nong.wd.dao.QuestionKeywordDao;
import com.ah3nong.wd.dao.ReplyDao;
import com.ah3nong.wd.dao.UserDao;
import com.ah3nong.wd.exception.ServiceException;
import com.ah3nong.wd.service.QuestionService;
import com.ah3nong.wd.util.JsonUtil;

@SuppressWarnings("unchecked")
public class QuestionServiceImpl implements QuestionService {
	private QuestionDao questionDao;
	private QuestionHisDao questionHisDao;
	private ProbingDao probingDao;
	private ReplyDao replyDao;
	private UserDao userDao;
	private ExpertDao expertDao;
	private DomainDao domainDao;
	private QuestionKeywordDao questionKeywordDao;
	private Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

	/**
	 * 根据页面参数，查询分页需要的数据
	 * 
	 * @param params 参数
	 * @param currenPage 当前页数
	 * @param pageSize 每页大小
	 * @return
	 * @throws ServiceException
	 */

	public Pager<Question> getQuestionPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException {
		String method = "getQuestionPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException("cannot query any questions since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:" + currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		if (params.get("status") == null) {
			params.put("status1", Config.getInt("QUESTION_REMOVED")+","+Config.getInt("QUESTION_AUDITING")+","+Config.getInt("QUESTION_FAILED_AUDITING"));
		} else {
			params.put("status2", params.get("status"));
		}

		Pager<Question> pager = new Pager<Question>(currentPage, pageSize);
		try {
			List<Question> qList = questionDao.queryForPager(params);
			for(int i=0;i<qList.size();i++){
				List<QuestionKeyword> qkList = questionKeywordDao.findKeywordsByQuestionId(qList.get(i).getId());
				if(qkList.size()>0){
					StringBuilder sb01 = new StringBuilder(); 
					for(int j=0;j<qkList.size();j++){
						sb01.append(qkList.get(j).getKeyword());
						sb01.append(",");
					}
					String tmp  = sb01.toString();
					qList.get(i).setKeywords(tmp.substring(0,tmp.length()-1));
				}
			}
			pager.setPageRecords(qList);
			pager.setTotalRecords(questionDao.countForPager(params));

			for (Question q : pager.getPageRecords()) {
				System.out.print(q.getUser().getUsername());
			}
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query pager data." + e);
			throw new ServiceException("question dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	@Transactional(rollbackFor = Exception.class)
	public int addQuestion(Question question, QuestionExpert questionExpert, List<QuestionImg> questionImgList) throws ServiceException {
		String method = "addQuestion";
		Integer id = 0;
		if (question == null) {
			log.error(method + ", the params of add question、questionExpert、questionExpert is null!");
			throw new ServiceException("cannot add question、questionExpert、questionExpert since the param is null!");
		}
		log.info(method + ", the question params:" + question.toString());
		try {
			//添加问题
			id = questionDao.addQuestion(question);
			
			//更新用户经验
			User user = userDao.findUserByPrimaryKey(question.getUserId());
			user.setExperience(user.getExperience()+Config.getInt("ASK_EXPERIENCE")-question.getExperience());
			userDao.updateUserExperience(user);
			
			String temp = question.getKeywords();
			if(temp!=null&&!"".equals(temp)){
				String[] qkws = temp.split(",");
				for(int i=0;i<qkws.length;i++){
					QuestionKeyword qk = new QuestionKeyword();
					qk.setQuestionId(question.getId());
					qk.setKeyword(qkws[i]);
					questionKeywordDao.addQuestionKeyword(qk); 
				}
			}

			if (questionExpert != null) {
				// 添加问题-专家记录
				questionExpert.setQuestionId(id);
				questionDao.addQuestionExpert(questionExpert);
				log.info(method + ", the questionExpert params:" + questionExpert.toString());
			}
			if (questionImgList != null && questionImgList.size() > 0) {
				// 添加问题-图片记录
				for (QuestionImg questionImg : questionImgList) {
					questionImg.setQuestionId(id);
					questionDao.addQuestionImg(questionImg);
					log.info(method + ", the questionImg params:" + questionImg.toString());
				}
			}
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while add question." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while add question data.Msg:" + e);
		}
		return id;
	}

	public int countAllQuestion(String terms) throws ServiceException {
		String method = "countAllQuestion";
		try {
			Map map = new HashMap();
			if (terms.equals("noReply")) {
				map.put("replyNum", 0);
			} else if (terms.equals("noResolve")) {
				map.put("status", 1);
			} else if (terms.equals("resolved")) {
				map.put("status", 2);
			} else if (terms.equals("recommend")) {
				map.put("recommended", 1);
			} else if (terms.equals("experience")){
				map.put("experience",0);
			}
			return questionDao.countAllQuestion(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countAll." + e);
			throw new ServiceException("question dao got exceptions while countAll data.Msg:" + e);
		}
	}

	// 根据领域分类的ID 查找问题的数量
	public int countAllByDomainId(String terms, int domainId) throws ServiceException {
		String method = "countAllQuestion";
		try {
			Map map = new HashMap();
			if (terms.equals("noReply")) {
				map.put("replyNum", 0);
			} else if (terms.equals("noResolve")) {
				map.put("status", 1);
			} else if (terms.equals("resolved")) {
				map.put("status", 2);
			} else if (terms.equals("recommend")) {
				map.put("recommended", 1);
			} else if (terms.equals("experience")){
				map.put("experience",0);
			}
			map.put("default", 03);
			map.put("domainId", "0" + domainId);
			Domain domain = domainDao.findDomainByPrimaryKey(domainId);
			map.put("nodePath", domain.getNodePath());
			return questionDao.countAllByDomainId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countAll." + e);
			throw new ServiceException("question dao got exceptions while countAll data.Msg:" + e);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public int delQuestionByPrimaryKey(int id) throws ServiceException {
		String method = "delQuestionByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of delete question is null!");
			throw new ServiceException("cannot delete question since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {

			// 判断该问题是否有追问
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", id);
			List<Probing> probingList = probingDao.findProbingByQuestionId(map);
			if (probingList.size() > 0) {
				probingDao.delProbingByQuestionId(id);
				log.info("delete questionProbing seccessfull with questionId=" + id);
			}
			// 判断该问题是否有回答
			List<Reply> replyList = replyDao.findReplyByQuestionId(map);
			if (replyList.size() > 0) {
				replyDao.delReplyByQuestionId(id);
				log.info("delete questionReply seccessfull with questionId=" + id);
			}

			// 判断是否关联专家
			List<QuestionExpert> qExpertList = questionDao.findQuestionExpertByQuestionId(id);
			log.info("find questionExpert list seccessfull!" + qExpertList);
			if (qExpertList.size() > 0) {
				questionDao.delQuestionExpertByQuestionId(id);
				log.info("delete questionExpert seccessfull with questionId=" + id);
			}
			// 判断是否有图片
			List<QuestionImg> qImgList = questionDao.findQuestionImgByQuestionId(id, 1);
			if (qImgList.size() > 0) {
				questionDao.delQuestionImgByQuestionId(id);
				log.info("delete questionImgs seccessfull with questionId=" + id);
			}
			int result = questionDao.delQuestionByPrimaryKey(id);
			return result;
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while delete question." + e);
			throw new ServiceException("question dao got exceptions while delete question data.Msg:" + e);
		}
	}

	public List<Question> findAllQuestion() throws ServiceException {
		String method = "findAllQuestion";
		try {
			return questionDao.findAllQuestion();
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while selectAll." + e);
			throw new ServiceException("question dao got exceptions while selectAll data.Msg:" + e);
		}
	}

	public List<Question> findQuestion(Map<String, Object> params) {
		return questionDao.findQuestion(params);
	}

	public List<Question> findLatestOpenedQuestion(int count) {
		return questionDao.findLatestOpenedQuestion(count);
	}

	public List<Question> findLatestSolvedQuestion(int count) {
		return questionDao.findLatestSolvedQuestion(count);
	}

	public List<Question> findRecommendedQuestion(int count) {
		return questionDao.findRecommendedQuestion(count);
	}

	public Question findQuestionByPrimaryKey(int id) throws ServiceException {
		String method = "findQuestionByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of query question by primaryKey is null!");
			throw new ServiceException("cannot query question by primaryKey since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return questionDao.findQuestionByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query question by primaryKey." + e);
			throw new ServiceException("question dao got exceptions while query question by primaryKey data.Msg:" + e);
		}
	}

	public int updateQuestionByPrimaryKey(Question question, boolean isSelective) throws ServiceException {
		String method = "updateQuestionByPrimaryKey";
		if (question == null) {
			log.error(method + ", the params of update question by primaryKey is null!");
			throw new ServiceException("cannot update question by primaryKey since the param is null!");
		}
		log.info(method + ", the params:" + question.toString());
		try {
			if (isSelective) {
				return questionDao.updateQuestionByPrimaryKeySelective(question);
			} else {
				return questionDao.updateQuestionByPrimaryKey(question);
			}
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while update question by primaryKey." + e);
			throw new ServiceException("question dao got exceptions while update question by primaryKey data.Msg:" + e);
		}
	}

	public int updateQuestionImgs(List<QuestionImg> questionImgs, int questionId) throws ServiceException {
		String method = "updateQuestionImgss";
		if (questionImgs.size() == 0 || questionId == 0) {
			log.error(method + ", the params of update questionImg is null!");
			throw new ServiceException("cannot update questionImg since the param is null!");
		}
		log.info(method + ", the params:" + questionImgs.toString() + ",questionId is:" + questionId);
		try {
			// 根据问题ID删除关联记录
			int DelResult = questionDao.delQuestionImgByQuestionId(questionId);
			// 如果删除执行成功则进行添加操作
			if (DelResult > 0) {
				for (QuestionImg questionImg : questionImgs) {
					questionDao.addQuestionImg(questionImg);
				}
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while update question by primaryKey." + e);
			throw new ServiceException("question dao got exceptions while update question by primaryKey data.Msg:" + e);
		}
	}

	public Expert findExpertByQuestionId(int questionId) throws ServiceException {
		String method = "findExpertByQuestionId";
		if (questionId == 0) {
			log.error(method + ", the params of query questionExpert by questionId is null!");
			throw new ServiceException("cannot query questionExpert by questionId since the param is null!");
		}
		log.info(method + ", the params:" + questionId);
		try {
			List<QuestionExpert> qExpertList = questionDao.findQuestionExpertByQuestionId(questionId);
			if (qExpertList.size() > 0 && qExpertList.get(0).getExpertId() != 0) {
				int expertId = qExpertList.get(0).getExpertId();
				Expert expert = expertDao.findExpertByID(expertId);
				return expert;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query questionExpert by questionId." + e);
			throw new ServiceException("question dao got exceptions while query questionExpert by questionId data.Msg:" + e);
		}
	}

	public List<QuestionImg> findImgsByQuestionId(int questionId, int status) throws ServiceException {
		String method = "findImgsByQuestionId";
		if (questionId == 0) {
			log.error(method + ", the params of query questionImgs by questionId is null!");
			throw new ServiceException("cannot query questionImgs by questionId since the param is null!");
		}
		log.info(method + ", the params:" + questionId);
		try {
			List<QuestionImg> qImgsList = questionDao.findQuestionImgByQuestionId(questionId, status);
			return qImgsList;
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query questionImgs by questionId." + e);
			throw new ServiceException("question dao got exceptions while query questionImgs by questionId data.Msg:" + e);
		}
	}

	public List<Map<String, Object>> getExpertAcceptedCountMap(int isAcceptedCount) throws ServiceException {
		String method = "findCountByExpert";
		if (isAcceptedCount == 0) {
			log.error(method, " the params isAcceptedCount is null!");
		}
		log.info(method + ", the params isAcceptedCount:" + isAcceptedCount);

		List<Map<String, Object>> num = new ArrayList<Map<String, Object>>();
		try {

			Map map = new HashMap();
			map.put("isAccepted", 1);
			map.put("isExpert", 1);
			map.put("isAcceptedCount", isAcceptedCount);
			num = questionDao.selectCountByParams(map);

		} catch (Exception e) {
			log.error(method + ",FindCountParams lookup failure!" + e);
			throw new ServiceException("FindCountParams lookup failure! data.Msg:" + e);
		}
		return num;
	}
	
	public List<Map<String, Object>> getUserAcceptCountMap(int isAcceptedCount) throws ServiceException {
		String method = "findCountByExpert";
		if (isAcceptedCount == 0) {
			log.error(method, " the params isAcceptedCount is null!");
		}
		log.info(method + ", the params isAcceptedCount:" + isAcceptedCount);

		List<Map<String, Object>> num = new ArrayList<Map<String, Object>>();
		try {

			Map map = new HashMap();
			map.put("isAccepted", 1);
			map.put("isExpert", 1);
			map.put("isAcceptedCount", isAcceptedCount);
			num = questionDao.selectUserAcceptCountByParams(map);

		} catch (Exception e) {
			log.error(method + ",FindCountParams lookup failure!" + e);
			throw new ServiceException("FindCountParams lookup failure! data.Msg:" + e);
		}
		return num;
	}
	@Override
	public List<Question> findQuestion(int domainId, boolean includeSelf, boolean includeChild, int start, int size) {
		if (!(includeSelf || includeChild)) {
			throw new java.lang.IllegalArgumentException("参数不正确。");
		}
		return null;
	}

	public List<Question> getAllQuestionsAndReplyNum(int currentPage, int pageSize, String terms, int domain, int t) throws ServiceException {
		String method = "getAllQuestionsAndReplyNum";
		if (currentPage == 0) {
			log.error(method, "the params: currentPage is " + currentPage);
		}
		if (pageSize == 0) {
			log.error(method, "the params: pageSize is " + pageSize);
		}
		log.info(method + ",  the params: currentPage is " + currentPage + "pageSize is " + pageSize);
		List<Question> questions = new ArrayList<Question>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			int start = (currentPage - 1) * pageSize;
			params.put("start", start);
			params.put("size", pageSize);

			// 所有分类
			if (domain == 0) {
				questions = questionDao.findQuestion(terms, start, pageSize);
			}
			// 指定了某个分类
			else {
				Domain d = domainDao.findDomainByPrimaryKey(domain);
				String nodePath = d.getNodePath();
				// 有子分类
				if (t == 1) {
					questions = questionDao.findQuestion(nodePath, true, true, terms, start, pageSize);
				}
				// 无子分类
				else {
					questions = questionDao.findQuestion(nodePath, true, false, terms, start, pageSize);
				}

			}
		} catch (Exception e) {
			log.error(method + ",FindCountParams lookup failure!" + e);
			throw new ServiceException("FindCountParams lookup failure! data.Msg:" + e);
		}

		return questions;
	}

	public List<Map<String, Object>> getMostDomainNameAndQuestionNumAll(String terms, int t, int domain) throws ServiceException {
		String method = "getMostDomainNameAndQuestionNumAll";
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

		try {
			if (terms.equals("noReply")) {
				questions = questionDao.findNoReplyQuestionsNum();
			} else if (terms.equals("noResolve")) {
				questions = questionDao.findNoResolveQuestionsNum();
			} else if (terms.equals("resolved")) {
				questions = questionDao.findResolvedQuestionsNum();
			} else if (terms.equals("recommend")) {
				questions = questionDao.findRecommendedQuestionsNum();
			} else {
				questions = questionDao.selectAllDomainNameAndQuestionNum();
			}

		} catch (Exception e) {
			log.error(method + ",FindCountParams lookup failure!" + e);
			throw new ServiceException("FindCountParams lookup failure! data.Msg:" + e);
		}
		return questions;
	}

	public List<Map<String, Object>> getMostDomainNameAndQuestionNum(String terms, int t, int domain) throws ServiceException {
		String method = "getMostDomainNameAndQuestionNum";
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

		try {
			Domain d = domainDao.findDomainByPrimaryKey(domain);
			t = d.hasChild() ? 1 : 0;
			// 无子分类
			if (t == 0) {
				if (terms.equals("noReply")) {
					questions = questionDao.findNoReplyQuestionsNum();
				} else if (terms.equals("noResolve")) {
					questions = questionDao.findNoResolveQuestionsNum();
				} else if (terms.equals("resolved")) {
					questions = questionDao.findResolvedQuestionsNum();
				} else if (terms.equals("recommend")) {
					questions = questionDao.findRecommendedQuestionsNum();
				} else {
					questions = null;
				}
			}
			// 有子分类
			else {
				Map map = new HashMap();
				map.put("nodePath", d.getNodePath());
				// map.put("domainId", "0" + domain);
				questions = questionDao.getDomainNameById(map);
			}
			log.info("getMostDomainNameAndQuestionNum the params:" + questions.toString());
		} catch (Exception e) {
			log.error(method + ",FindCountParams lookup failure!" + e);
			throw new ServiceException("FindCountParams lookup failure! data.Msg:" + e);
		}
		return questions;
	}

	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public void setProbingDao(ProbingDao probingDao) {
		this.probingDao = probingDao;
	}

	public void setQuestionKeywordDao(QuestionKeywordDao questionKeywordDao) {
		this.questionKeywordDao = questionKeywordDao;
	}

	public void setQuestionHisDao(QuestionHisDao questionHisDao) {
		this.questionHisDao = questionHisDao;
	}

	public int delQuestionImgByPrimaryKey(int questionImgId) throws ServiceException {
		String method = "delQuestionImgByPrimaryKey";
		if (questionImgId == 0) {
			log.error(method + ", the params of delete questionImg by questionImgId is null!");
			throw new ServiceException("cannot delete questionImg by questionImgId since the param is null!");
		}
		log.info(method + ", the params:" + questionImgId);
		try {
			int result = questionDao.delQuestionImgByPrimaryKey(questionImgId);
			return result;
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while delete questionImg by questionImgId." + e);
			throw new ServiceException("question dao got exceptions while delete questionImg by questionImgId data.Msg:" + e);
		}
	}

	public QuestionImg findQuestionImgByPrimaryKey(int questionImgId) throws ServiceException {
		String method = "findQuestionImgByPrimaryKey";
		if (questionImgId == 0) {
			log.error(method + ", the params of query questionImg by questionImgId  is null!");
			throw new ServiceException("cannot query questionImg by questionImgId since the param is null!");
		}
		log.info(method + ", the params:" + questionImgId);
		try {
			return questionDao.findQuestionImgById(questionImgId);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query questionImg by questionImgId." + e);
			throw new ServiceException("question dao got exceptions query questionImg by questionImgId data.Msg:" + e);
		}
	}

	public int updateQuestionImgs(QuestionImg questionImg, boolean isSelective) throws ServiceException {
		String method = "updateQuestionImgs";
		if (questionImg == null) {
			log.error(method + ", the params of update questionImg  is null!");
			throw new ServiceException("cannot update questionImg since the param is null!");
		}
		log.info(method + ", the params:" + questionImg.toString());
		try {
			return questionDao.updateQuestionImgByPrimaryKey(questionImg, isSelective);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while update questionImg." + e);
			throw new ServiceException("question dao got exceptions while update questionImg data.Msg:" + e);
		}
	}

	public void addQuestionHis(QuestionHis questionHis) throws ServiceException {
		String method = "addQuestionHis";
		if (questionHis == null) {
			log.error(method + ", the params of add questionHis  is null!");
			throw new ServiceException("cannot add questionHis since the param is null!");
		}
		log.info(method + ", the params:" + questionHis);
		try {
			questionHisDao.addQuestionHis(questionHis);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while add questionHis." + e);
			throw new ServiceException("question dao got exceptions while add questionHis data.Msg:" + e);
		}
	}

	public Pager<Question> getQuestionHisPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException {
		String method = "getQuestionHisPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException("cannot query any questionHis since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:" + currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		// params.put("status", Config.getInt("QUESTION_REMOVED"));
		Pager<Question> pager = new Pager<Question>(currentPage, pageSize);
		try {
			pager.setPageRecords(questionHisDao.queryForPager(params));
			pager.setTotalRecords(questionHisDao.countForPager(params));

		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query pager data." + e);
			throw new ServiceException("question dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	public void addQuestionExtraContentHis(QuestionExtraContentHis qExtraContentHis) throws ServiceException {
		String method = "addQuestionExtraContentHis";
		if (qExtraContentHis == null) {
			log.error(method + ", the params of add qExtraContentHis  is null!");
			throw new ServiceException("cannot add qExtraContentHis since the param is null!");
		}
		log.info(method + ", the params:" + qExtraContentHis);
		try {
			questionHisDao.addQuestionExtraContentHis(qExtraContentHis);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while add qExtraContentHis." + e);
			throw new ServiceException("question dao got exceptions while add qExtraContentHis data.Msg:" + e);
		}

	}

	public Pager<QuestionExtraContentHis> getQuestionExtraContentHisPagerByParam(Map params, int currentPage, int pageSize) throws ServiceException {
		String method = "getQuestionExtraContentHisPagerByParam";
		if (params == null) {
			log.error(method + ", the params of pager data is null!");
			throw new ServiceException("cannot query any qExtraContentHis since the param is null!");
		}
		if (currentPage <= 0) {
			log.error(method + ", the current page is invalid!");
			throw new ServiceException("the current page is invalid!");
		}
		if (pageSize <= 0) {
			log.error(method + ", the pageSize is invalid!");
			throw new ServiceException("the pageSizeis invalid!");
		}
		log.info(method + ", the params:" + params.toString() + ",currentPage:" + currentPage + ",pagesize:" + pageSize);

		params.put("start", (currentPage - 1) * pageSize);
		params.put("size", pageSize);
		// params.put("status", Config.getInt("QUESTION_REMOVED"));
		Pager<QuestionExtraContentHis> pager = new Pager<QuestionExtraContentHis>(currentPage, pageSize);
		try {
			pager.setPageRecords(questionHisDao.queryExtraContentForPager(params));
			pager.setTotalRecords(questionHisDao.countExtraContentForPager(params));
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query pager data." + e);
			throw new ServiceException("question dao got exceptions while query pager data.Msg:" + e);
		}

		return pager;
	}

	public QuestionExtraContentHis getQuestionExtraContentHisByPrimaryKey(int id) throws ServiceException {
		String method = "getQuestionExtraContentHisByPrimaryKey";
		if (id == 0) {
			log.error(method + ", the params of query questionExtraContentHis by id  is null!");
			throw new ServiceException("cannot query questionExtraContentHis by id since the param is null!");
		}
		log.info(method + ", the params:" + id);
		try {
			return questionHisDao.findQEContentHisByPrimaryKey(id);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query questionExtraContentHis by id." + e.getLocalizedMessage());
			throw new ServiceException("question dao got exceptions query questionExtraContentHis by id data.Msg:" + e.getLocalizedMessage());
		}
	}

	public QuestionHis getQuestionHisByPrimaryKey(int questionHisId) throws ServiceException {
		String method = "getQuestionHisByPrimaryKey";
		if (questionHisId == 0) {
			log.error(method + ", the params of query questionHis by id  is null!");
			throw new ServiceException("cannot query questionHis by id since the param is null!");
		}
		log.info(method + ", the params:" + questionHisId);
		try {
			return questionHisDao.findQuestionHisByPrimaryKey(questionHisId);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while query questionHis by id." + e.getLocalizedMessage());
			throw new ServiceException("question dao got exceptions query questionHis by id data.Msg:" + e.getLocalizedMessage());
		}
	}

	public int updateQuestionHisSelective(QuestionHis questionHis) throws ServiceException {
		String method = "updateQuestionHisSelective";
		if (questionHis == null) {
			log.error(method + ", the params of update questionHis  is null!");
			throw new ServiceException("cannot update questionHis since the param is null!");
		}
		log.info(method + ", the params:" + questionHis);
		try {
			return questionHisDao.updateQuestionHisByPrimaryKeySelective(questionHis);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while update questionHis." + e.getLocalizedMessage());
			throw new ServiceException("question dao got exceptions while update questionHis data.Msg:" + e.getLocalizedMessage());
		}
	}

	public int updateQExHisSelective(QuestionExtraContentHis exHis) throws ServiceException {
		String method = "updateQExHisSelective";
		if (exHis == null) {
			log.error(method + ", the params of update qExHis  is null!");
			throw new ServiceException("cannot update qExHis since the param is null!");
		}
		log.info(method + ", the params:" + exHis);
		try {
			return questionHisDao.updateQExHisSelective(exHis);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while update qExHis." + e.getLocalizedMessage());
			throw new ServiceException("question dao got exceptions while update qExHis data.Msg:" + e.getLocalizedMessage());
		}
	}

	public List<Map<String, Object>> getQuestionsBuExpertId(int expertId, int start, int size) throws ServiceException {
		String method = "getQuestionsBuExpertId";
		if (expertId == 0) {
			log.error(method + "the param is null");
			throw new ServiceException("got Questions by expertId is null!");
		}
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("expertId", expertId);
			map.put("start", start - 1);
			map.put("size", size);
			questions = questionDao.findQuestionsByExpertId(map);
		} catch (Exception e) {
			log.error(method + ",got Questions by expertId is null!" + e.getLocalizedMessage());
			throw new ServiceException("qgot Questions by expertId data.Msg:" + e.getLocalizedMessage());
		}
		return questions;
	}

	public Long getCountQuestionsByExpertId(int expertId) throws ServiceException {
		String method = "getCountQuestionsByExpertId";
		if (expertId == 0) {
			log.error(method + "the param is null");
			throw new ServiceException("got Questions by expertId is null!");
		}
		Map map = new HashMap();
		map.put("expertId", expertId);
		return questionDao.countQuestionsByExpertId(map);
	}

	public List<Map<String, Object>> searchByTerm(String terms, int start, int size, int status) throws ServiceException {
		String method = "searchByTerm";
		if (terms == null) {
			throw new ServiceException("search by terms is null!");
		}
		log.info(method + ", search questions by term.");
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("start", start);
			map.put("size", size);
			map.put("keyword", terms);
			if (status != 0) {
				map.put("status", status);
			}
			questions = questionDao.searchByTerm(map);
		} catch (Exception e) {
			log.error(method + ",search by terms is null!" + e.getLocalizedMessage());
			throw new ServiceException("search by terms is null.Msg:" + e.getLocalizedMessage());
		}
		return questions;
	}

	public Long CountQuestionsSearchbyTerms(String terms, int status) throws ServiceException {
		String method = "CountQuestionsSearchbyTerms";
		if (terms == null) {
			throw new ServiceException("search by terms is null!");
		}
		log.info(method + ", search questions by term.");
		Long l = null;
		try {
			Map map = new HashMap();
			map.put("keyword", terms);
			if (status != 0) {
				map.put("status", status);
			}
			l = questionDao.CountQuestionsSearchByTerms(map);
		} catch (Exception e) {
			log.error(method + ",search by terms is null!" + e.getLocalizedMessage());
			throw new ServiceException("search by terms is null.Msg:" + e.getLocalizedMessage());
		}
		return l;
	}

	// countQuestionsByExpertId

	public List<QuestionImg> findRecommendQuestionWithImg() throws ServiceException {
		String method = "findRecommendQuestionWithImg";
		try {
			return questionDao.findRecomendQuestionWithImgs();
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while find recommendQuestion with Imgs." + e);
			throw new ServiceException("question dao got exceptions while  find recommendQuestion with Imgs data.Msg:" + e);
		}
	}

	public List<Question> findQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findQuestionsByUserId";
		try {
			return questionDao.findQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findQuestionsByUserId data.Msg:" + e);
		}
	}

	public List<Question> findResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findResolvedQuestionsByUserId";
		try {
			return questionDao.findResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public List<Question> findNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findNoResolvedQuestionsByUserId";
		try {
			return questionDao.findNoResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findNoResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findNoResolvedQuestionsByUserId data.Msg:" + e);
		}
	}

	public int countQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countQuestionsByUserId";
		try {
			return questionDao.countQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public int countQuestionsHaveReplyByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countQuestionsByUserId";
		try {
			return questionDao.countQuestionsHaveReplyByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countQuestionsByUserId data.Msg:" + e);
		}
	}

	public int countResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countResolvedQuestionsByUserId";
		try {
			return questionDao.countResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public int countNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countNoResolvedQuestionsByUserId";
		try {
			return questionDao.countNoResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countnoResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countnoResolvedQuestionsByUserId data.Msg:" + e);
		}
	}

	public List<Question> findReplyQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findReplyQuestionsByUserId";
		try {
			return questionDao.findReplyQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findReplyQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findReplyQuestionsByUserId data.Msg:" + e);
		}
	}

	public List<Question> findReplyResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findReplyResolvedQuestionsByUserId";
		try {
			return questionDao.findReplyResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findReplyResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findReplyResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public List<Question> findReplyNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "findReplyNoResolvedQuestionsByUserId";
		try {
			return questionDao.findReplyNoResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findReplyNoResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findReplyNoResolvedQuestionsByUserId data.Msg:" + e);
		}
	}

	public int countReplyQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countReplyQuestionsByUserId";
		try {
			return questionDao.countReplyQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countReplyQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countReplyQuestionsByUserId data.Msg:" + e);
		}
	}

	public int countReplyResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countReplyResolvedQuestionsByUserId";
		try {
			return questionDao.countReplyResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countReplyResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countReplyResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public int countReplyNoResolvedQuestionsByUserId(Map<String, Object> params) throws ServiceException {
		String method = "countReplyNoResolvedQuestionsByUserId";
		try {
			return questionDao.countReplyNoResolvedQuestionsByUserId(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countReplyNoResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countReplyNoResolvedQuestionsByUserId data.Msg:" + e);
		}
	}

	public int countQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "countQuestionsAskForExpert";
		try {
			return questionDao.countQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countQuestionsAskForExpert data.Msg:" + e);
		}
	}

	public List<Question> findQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "findQuestionsAskForExpert";
		try {
			return questionDao.findQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findQuestionsAskForExpert data.Msg:" + e);
		}
	}

	public int countResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "countResoveQuestionsAskForExpert";
		try {
			return questionDao.countResoveQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countResoveQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countResoveQuestionsAskForExpert data.Msg:" + e);
		}
	}
	
	public int countNoResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "countNoResoveQuestionsAskForExpert";
		try {
			return questionDao.countNoResoveQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countNoResoveQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countNoResoveQuestionsAskForExpert data.Msg:" + e);
		}
	}
	
	public List<Question> findNoResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "findNoResoveQuestionsAskForExpert";
		try {
			return questionDao.findNoResoveQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findNoResoveQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findNoResoveQuestionsAskForExpert data.Msg:" + e);
		}
	}
	
	public List<Question> findResoveQuestionsAskForExpert(Map<String, Object> params) throws ServiceException {
		String method = "findResoveQuestionsAskForExpert";
		try {
			return questionDao.findResoveQuestionsAskForExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findResoveQuestionsAskForExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findResoveQuestionsAskForExpert data.Msg:" + e);
		}
	}

	public Long getCountQuestionsAskByExpert(int expertId) throws ServiceException {
		String method = "getCountQuestionsAskByExpert";
		if (expertId == 0) {
			log.error(method + "the param is null");
			throw new ServiceException("got Questions ask by expert is null!");
		}
		Map map = new HashMap();
		map.put("expertId", expertId);
		map.put("status", 3);
		return questionDao.countQuestionsAskByExpert(map);
	}

	public List<Map<String, Object>> getQuestionsAskByExpert(int expertId, int start, int size) throws ServiceException {
		String method = "getQuestionsAskByExpert";
		if (expertId == 0) {
			log.error(method + "the param is null");
			throw new ServiceException("got Questions by expertId is null!");
		}
		List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
		try {
			Map map = new HashMap();
			map.put("expertId", expertId);
			map.put("start", start - 1);
			map.put("size", size);
			map.put("status", 3);
			questions = questionDao.findQuestionsAskByExpert(map);
		} catch (Exception e) {
			log.error(method + ",got Questions ask by expert is null!" + e.getLocalizedMessage());
			throw new ServiceException("qgot Questions ask by expert data.Msg:" + e.getLocalizedMessage());
		}
		return questions;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

	@Override
	public String getRecommendQuestionsForApi(int limit) {
		if (limit == 0) {
			limit = 12;
		}
		List<Question> list = questionDao.getRecommendQuestionsForApi(limit);
		List<Map> listMap = new ArrayList<Map>();
		for (Question s : list) {
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("subject", s.getSubject());
			map1.put("url", "http://qa.longcom.com/question/" + s.getId() + ".html");
			listMap.add(map1);
		}
		if (listMap != null && listMap.size() > 0) {
			return JsonUtil.list2json(listMap);
		} else {
			return null;
		}
	}

	@Override
	public String getHeadQuestionsForApi(int limit) {
		if (limit == 0) {
			limit = 4;
		}
		List<Question> list = questionDao.getHeadQuestionsForApi(limit);
		List<Map> listMap = new ArrayList<Map>();
		for (Question s : list) {
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("subject", s.getSubject());
			map1.put("url", "http://qa.longcom.com/question/" + s.getId() + ".html");
			listMap.add(map1);
		}
		if (listMap != null && listMap.size() > 0) {
			return JsonUtil.list2json(listMap);
		} else {
			return null;
		}
	}

	@Override
	public String getQuestionWithImage() {
		List<Map<String, Object>> mapQuestion = questionDao.getQuestionWithImage();
		if (mapQuestion != null && mapQuestion.size() > 0) {
			Map<String, Object> imageQuestion = mapQuestion.get(0);
			imageQuestion.put("imageUrl", "http://qa.longcom.com/question/" + imageQuestion.get("url"));
			imageQuestion.put("url", "http://qa.longcom.com/question/" + imageQuestion.get("id") + ".html");
			imageQuestion.remove("id");
			return "[" + JsonUtil.map2json(imageQuestion) + "]";
		} else {
			return null;
		}

	}

	// 获得首页图片问题的 图片url和问题id
	public List<Map<String, Object>> getQuestionWithRecommendImage() {
		String method = "getQuestionWithRecommendImage";
		List<Map<String, Object>> mapQuestion = questionDao.getQuestionWithImage();
		if (mapQuestion.size() == 0) {
			log.error(method + "the param is null");
			try {
				throw new ServiceException("getQuestionWithRecommendImage is null!");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return null;
		}
		return mapQuestion;
	}

	public List<Map<String, Object>> getQuestionsRecommendToExpert(Map<String, Object> params) throws ServiceException {
		String method = "getQuestionsRecommendToExpert";
		try {
			return questionDao.getQuestionsRecommendToExpert(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while getQuestionsRecommendToExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while getQuestionsRecommendToExpert data.Msg:" + e);
		}
	}

	public int getQuestionsRecommendToExpertNum(Map<String, Object> params) throws ServiceException {
		String method = "getQuestionsRecommendToExpertNum";
		try {
			return questionDao.getQuestionsRecommendToExpertNum(params);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while getQuestionsRecommendToExpertNum." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while getQuestionsRecommendToExpertNum data.Msg:" + e);
		}
	}

	@Override
	public List<Map<String, Object>> getRecommendQuestionsWithImage() {
		String method = "getQuestionWithRecommendImage";
		List<Map<String, Object>> mapQuestion = questionDao.getRecommendQuestionsWithImage();
		if (mapQuestion.size() == 0) {
			log.error(method + "the param is null");
			try {
				throw new ServiceException("getQuestionWithRecommendImage is null!");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return null;
		}
		return mapQuestion;
	}
	
	@Override
	public List<Map<String, Object>> findImgQuestions(Map<String,Object> map) {
		String method = "getQuestionWithRecommendImage";
		List<Map<String, Object>> mapQuestion = questionDao.findImgQuestions(map);
		if (mapQuestion.size() == 0) {
			log.error(method + "the param is null");
			try {
				throw new ServiceException("getQuestionWithRecommendImage is null!");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return null;
		}
		return mapQuestion;
	}

	@Override
	public void updateStatusByCreatedTime(Map<String, Object> map) {
		questionDao.updateStatusByCreatedTime(map);
	}

	@Override
	public Question findBySubjectAndCreatedTime(Question question) {
		return questionDao.findBySubjectAndCreatedTime(question);
	}

	@Override
	public List<Question> findCommentQuestionByUserId(Map<String, Object> map) throws ServiceException {
		String method = "findCommentQuestionByUserId";
		try {
			return questionDao.findCommentQuestionByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while getQuestionsRecommendToExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while getQuestionsRecommendToExpert data.Msg:" + e);
		}
	}

	@Override
	public int countCommentQuestionByUserId(Map<String, Object> map) throws ServiceException {
		String method = "countCommentQuestionByUserId";
		try {
			return questionDao.countCommentQuestionByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countResolvedQuestionsByUserId data.Msg:" + e);
		}
	}

	@Override
	public List<Question> findCommentQuestionResovedByUserId(Map<String, Object> map) throws ServiceException {
		String method = "findCommentQuestionResovedByUserId";
		try {
			return questionDao.findCommentQuestionResovedByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while getQuestionsRecommendToExpert." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while getQuestionsRecommendToExpert data.Msg:" + e);
		}
	}

	@Override
	public int countCommentQuestionResovedByUserId(Map<String, Object> map) throws ServiceException {
		String method = "countCommentQuestionResovedByUserId";
		try {
			return questionDao.countCommentQuestionResovedByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	@Override
	public List<Question> findCommentQuestionNoResovedByUserId(Map<String, Object> map) throws ServiceException {
		String method = "findCommentQuestionNoResovedByUserId";
		try {
			return questionDao.findCommentQuestionNoResovedByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while findCommentQuestionNoResovedByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while findCommentQuestionNoResovedByUserId data.Msg:" + e);
		}
	}
	
	@Override
	public int countCommentQuestionNoResovedByUserId(Map<String, Object> map) throws ServiceException {
		String method = "countCommentNoQuestionResovedByUserId";
		try {
			return questionDao.countCommentQuestionNoResovedByUserId(map);
		} catch (Exception e) {
			log.error(method + ",question dao got exceptions while countNoResolvedQuestionsByUserId." + e);
			e.printStackTrace();
			throw new ServiceException("question dao got exceptions while countNoResolvedQuestionsByUserId data.Msg:" + e);
		}
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int countTodayQuestion() {
		return questionDao.countTodayQuestion();
	}

	@Override
	public int countTodayReply() {
		return questionDao.countTodayReply();
	}

	@Override
	public List<Question> findMostViewCountQuestions(int count) {
		return questionDao.findMostViewCountQuestions(count);
	}

	@Override
	public List<Map<String, Object>> findDomainAndQusetionNumAsk(Map<String, Object> map) {
		return questionDao.findDomainAndQusetionNumAsk(map);
	}

	@Override
	public List<Question> findQuestionsAsk(Map<String, Object> map) {
		return questionDao.findQuestionsAsk(map);
	}

	@Override
	public int countQuestionsAsk(Map<String, Object> map) {
		return questionDao.countQuestionsAsk(map);
	}

	@Override
	public List<Map<String, Object>> findImgQuestionsAsk(Map<String, Object> map) {
		return questionDao.findImgQuestionsAsk(map);
	}

	@Override
	public List<Map<String, Object>> findNoImgQuestions(Map<String, Object> map) {
		return questionDao.findNoImgQuestions(map);
	}

	@Override
	public List<Map<String, Object>> findNoImgQuestionsByParentId(Map<String, Object> map) {
		return questionDao.findNoImgQuestionsByParentId(map);
	}

	@Override
	public void updateStatusByDomainId(Question question) {
		questionDao.updateStatusByDomainId(question);
	}
}
