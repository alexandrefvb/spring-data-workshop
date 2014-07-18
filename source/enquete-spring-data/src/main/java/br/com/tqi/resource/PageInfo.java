package br.com.tqi.resource;

import org.springframework.data.domain.Page;

public class PageInfo {

    private Page<?> page;

    PageInfo(Page<?> page) {
	this.page = page;
    }

    public int getNumber() {
	return this.page.getNumber();
    }

    public int getSize() {
	return this.page.getSize();
    }

    public int getTotalPages() {
	return this.page.getTotalPages();
    }

    public long getTotalElements() {
	return this.page.getTotalElements();
    }
}
