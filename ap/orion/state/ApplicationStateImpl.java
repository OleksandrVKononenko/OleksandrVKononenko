package ap.orion.state;

import ap.utils.Biu;

public class ApplicationStateImpl {

	private int state = 0x0;

	public ApplicationStateImpl() {

		this.set_multi_selectable(true);
	}

	public int get_state() {
		return state;
	}

	public void set_state(int state) {
		this.state = state;
	}

	public boolean is_accessable() {

		return Biu.ISA(this.get_state(), ApplicationState.ACCESSABLE);
	}

	public void set_accessable(boolean accessable) {

		if (accessable) {
			this.set_state(Biu.ON(this.get_state(), ApplicationState.ACCESSABLE));
		} else {
			this.set_state(Biu.OFF(this.get_state(), ApplicationState.ACCESSABLE));
		}
	}

	public boolean is_multi_selectable() {

		return Biu.ISA(this.get_state(), ApplicationState.MULTI_SELECTION);
	}

	public void set_multi_selectable(boolean multi_selectable) {

		if (multi_selectable) {
			this.set_state(Biu.ON(this.get_state(), ApplicationState.MULTI_SELECTION));
		} else {
			this.set_state(Biu.OFF(this.get_state(), ApplicationState.MULTI_SELECTION));
		}
	}

	public static ApplicationStateImpl get_instance() {
		return new ApplicationStateImpl();
	}

}
