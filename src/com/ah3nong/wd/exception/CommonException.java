package com.ah3nong.wd.exception;

/**
 * The super exception class
 * 
 */
public abstract class CommonException extends Exception {

    private static final long serialVersionUID = 21100001L;

    private String _errcode;

    /**
     * for completeness, no point to use this constructor
     */
    public CommonException() {
        super();
    }

    /**
     * @param errcode
     */
    public CommonException(String errcode) {
        super();
        _errcode = errcode;
    }

    /**
     * @param errcode
     * @param s
     */
    public CommonException(String errcode, String s) {
        super(s);
        _errcode = errcode;
    }

    /**
     * @param errcode
     * @param t
     */
    public CommonException(String errcode, Throwable t) {
        super(t);
        _errcode = errcode;
    }

    /**
     * @param errcode
     * @param s
     * @param t
     */
    public CommonException(String errcode, String s, Throwable t) {
        super(s, t);
        _errcode = errcode;
    }

    /**
     * @return String
     */
    public String getErrorCode() {
        return _errcode;
    }

    /**
     * @return String
     */
    public String toString() {
        return super.toString() + " [ErrorCode: " + _errcode + "]";
    }
}
