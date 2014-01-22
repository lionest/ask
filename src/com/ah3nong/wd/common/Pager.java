package com.ah3nong.wd.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @param <T>
 *            Object
 */
public class Pager<T> {
	
	public static int DEFAULT_PAGE_SIZE = 10;

    private int currentPage;

    // The number of records in one page
    private int pageSize = DEFAULT_PAGE_SIZE;

    // The total number of records in DB
    private int totalRecords;

    // The total number of page
    private int pageCount;

    // The number where we begin to get record
    private int startRecord;

    // Whether it has previous page
    private boolean hasPreviousPage;

    // Whether it has next page
    private boolean hasNextPage;

    // Whether it has only one page
    private boolean onlyOnePage;

    // The records of page designed
    private List<T> pageRecords;

    public int getCurrentPage() {
        return currentPage;
    }

    /**
	 * 构造方法，只构造空页.
	 */
	public Pager(int currentPage,int pageSize) {
		this(currentPage, 0, pageSize, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public Pager(int currentPage, int totalSize, int pageSize, List data) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecords = totalSize;
		this.pageRecords = data;
	}
	
    /**
     * make sure the page is in the range of the total pages
     * 
     * @param currentPage
     *            current page
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1) {
            this.currentPage = 1;
            return;
        }
        if (currentPage > getPageCount()) {
            this.currentPage = getPageCount();
            return;
        }
        this.currentPage = currentPage;
    }

    /**
     * get page size
     * 
     * @return page size number
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * set page size
     * 
     * @param pageSize
     *            page size number
     */
    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * get total records
     * 
     * @return total record's number
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * set total records
     * 
     * @param totalRecords
     *            total record number
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * Get the total count of the page
     * 
     * @return count number
     */
    public int getPageCount() {
        // If there is no data in database.
        if (totalRecords == 0) {
            pageCount = 1;
            return pageCount;
        }
        boolean isZero = totalRecords % pageSize == 0;
        pageCount = totalRecords / pageSize;
        pageCount = isZero ? pageCount : pageCount + 1;
        return pageCount;
    }

    /**
     * First record of one page
     * 
     * @return start records
     */
    public int getStartRecord() {
        startRecord = ((currentPage - 1) * pageSize);
        return startRecord;
    }

    /**
     * Whether has previous page
     * 
     * @return if previous page's is exist,return true else not
     */
    public boolean isHasPreviousPage() {
        hasPreviousPage = (currentPage == 1) ? false : true;
        return hasPreviousPage;
    }

    /**
     * Whether has next page
     * 
     * @return if next page's is exist,return true else not
     */
    public boolean isHasNextPage() {
        hasNextPage = (currentPage == getPageCount()) ? false : true;
        return hasNextPage;
    }

    /**
     * Whether is only one page
     * 
     * @return if only one page,return true else not
     */
    public boolean isOnlyOnePage() {
        onlyOnePage = ((getPageCount() == 1) ? true : false);
        return onlyOnePage;
    }

    /**
     * @return the pageRecords
     */
    public List<T> getPageRecords() {
        return pageRecords;
    }

    /**
     * @param pageRecords
     *            the pageRecords to set
     */
    public void setPageRecords(List<T> pageRecords) {
        this.pageRecords = pageRecords;
    }
    
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("{pageSize=").append(pageSize).append(", currentPage=")
    	.append(currentPage).append(", totalSize=").append(totalRecords)
    	.append(",pageRecords size =").append(pageRecords.size()).append("}");
    	return sb.toString();
    }
}
