package pl.poznan.ilim.havok.entities;

import java.io.Serializable;
import java.util.Date;

public class ResultBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T[] results;

	private Date fetchDate;

	public ResultBean() {
	};

	public ResultBean(T[] results, Date fetchDate) {
		this.results = results;
		this.fetchDate = fetchDate;
	}

	public T[] getResults() {
		return results;
	}

	public void setResults(T[] results) {
		this.results = results;
	}

	public Date getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(Date fetchDate) {
		this.fetchDate = fetchDate;
	}
}
