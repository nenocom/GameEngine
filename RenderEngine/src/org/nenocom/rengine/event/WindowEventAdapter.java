package org.nenocom.rengine.event;

public class WindowEventAdapter extends WindowEventHandler{
	
	public WindowEventAdapter(long windowHandle) {
		super(windowHandle);
	}
	
	@Override
	public void onWindowFocus() {
		
	}

	@Override
	public void onWindowRestore() {
		
	}

	@Override
	public void onWindowMinimize() {
		
	}

	@Override
	public void onSizeChanged(int width, int height) {
		
	}
	
	@Override
	public void onWindowLostFocus() {
		
	}
	
	@Override
	public void onMouseWheelMove(double yoffset) {

	}
	
	@Override
	public void onMouseRelease(int button, int mods) {

	}

	@Override
	public void onMouseClick(int button, int mods) {

	}

	@Override
	public void onKeyRepeat(int key, int scancode, int mods) {

	}

	@Override
	public void onMouseMove(double xpos, double ypos) {

	}

	@Override
	public void onKeyPress(int key, int scancode, int mods) {

	}

	@Override
	public void onKeyRelease(int key, int scancode, int mods) {

	}

	@Override
	public void onMouseExit() {

	}

	@Override
	public void onMouseEnter() {

	}
	
}
