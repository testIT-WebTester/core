package info.novatec.testit.webtester.api.browser;

import java.util.UUID;

import info.novatec.testit.webtester.browser.BrowserRegistry;
import info.novatec.testit.webtester.eventsystem.events.browser.AbstractBrowserEvent;


/**
 * Unique identifier for a {@link Browser browser}.
 * <p>
 * Instances of this class are used to identify browsers in the
 * {@link BrowserRegistry registry} or to trace {@link AbstractBrowserEvent
 * events} back to the browser they originated in.
 *
 * @see BrowserRegistry
 * @see AbstractBrowserEvent
 * @since 0.9.0
 */
public class BrowserIdentification {

    private UUID uid;

    /**
     * Creates a new {@link BrowserIdentification} using the current nanosecond
     * time of this machine as the unique identifier.
     *
     * @since 0.9.0
     */
    public BrowserIdentification() {
        this.uid = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + ((uid == null) ? 0 : uid.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BrowserIdentification other = ( BrowserIdentification ) obj;
        if (uid == null) {
            if (other.uid != null) {
                return false;
            }
        } else if (!uid.equals(other.uid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return uid.toString();
    }

}
