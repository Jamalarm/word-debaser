package debaser;

import state.IState;
import state.StateGenerationUtil;

public class DebaserService {

    public static void main(String[] args) {
        IState state = StateGenerationUtil.generateRandomState(0.5);
        System.out.println(state);
    }

}
