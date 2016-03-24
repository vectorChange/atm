package com.we.view;

import java.beans.PropertyChangeEvent;

import com.we.controller.AbstractController;

public interface AbstractView {
	public void modelPropertyChange(PropertyChangeEvent evt) ;

    public void setController(AbstractController controller);
}
