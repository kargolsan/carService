package Application.Services;

import java.util.Locale;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 19.08.2016
 * Time: 18:03
 */
public class PropertiesUtf8DecodeService {
    /**
     * Gets the unicode friendly resource bundle
     *
     * @param baseName
     * @see ResourceBundle#getBundle(String)
     * @return Unicode friendly resource bundle
     */
    public static final ResourceBundle getBundle(final String baseName, final Locale locale) {

        return createUtf8PropertyResourceBundle(
                ResourceBundle.getBundle(baseName, locale));
    }

    /**
     * Creates unicode friendly {@link PropertyResourceBundle} if possible.
     *
     * @param bundle
     * @return Unicode friendly property resource bundle
     */
    private static ResourceBundle createUtf8PropertyResourceBundle(
            final ResourceBundle bundle) {
        if (!(bundle instanceof PropertyResourceBundle)) {
            return bundle;
        }
        return new Utf8PropertyResourceBundle((PropertyResourceBundle) bundle);
    }

    /**
     * Resource Bundle that does the hard work
     */
    private static class Utf8PropertyResourceBundle extends ResourceBundle {

        /**
         * Bundle with unicode data
         */
        private final PropertyResourceBundle bundle;

        /**
         * Initializing constructor
         *
         * @param bundle
         */
        private Utf8PropertyResourceBundle(final PropertyResourceBundle bundle) {
            this.bundle = bundle;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Enumeration getKeys() {
            return bundle.getKeys();
        }

        @Override
        protected Object handleGetObject(final String key) {
            final String value = bundle.getString(key);
            if (value == null)
                return null;
            try {
                return new String(value.getBytes("ISO-8859-1"), "UTF-8");
            } catch (final UnsupportedEncodingException e) {
                throw new RuntimeException("Encoding not supported", e);
            }
        }
    }
}
