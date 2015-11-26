package info.novatec.testit.webtester.api.callbacks;

/**
 * A functional callback interface with one parameter of type A and no return
 * value.
 *
 * @param <P> the parameter type of the callback method
 * @since 0.9.0
 */
public interface Callback<P> {

    /**
     * This method is called by an action template and contains the logic of the
     * action to execute.
     *
     * @param arg the input parameter
     * @since 0.9.0
     */
    void execute(P arg);

}
