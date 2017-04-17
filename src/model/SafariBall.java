package model;

public class SafariBall extends Item {

	public SafariBall() {
		super("Safari Ball");
	}

	@Override
	String examineMessage() {
		return "A special ball that is used only in the Safari Zone.";
	}
}
