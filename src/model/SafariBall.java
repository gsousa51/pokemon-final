package model;

import java.io.Serializable;

public class SafariBall extends Item implements Serializable{

    private static final long serialVersionUID = 1L;

    public SafariBall() {
		super("Safari Ball");
	}

	@Override
	public String examineMessage() {
		return "A special ball that is used only in the Safari Zone.";
	}
}
