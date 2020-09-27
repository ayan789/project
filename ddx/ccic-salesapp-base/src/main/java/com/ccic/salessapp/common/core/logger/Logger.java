package com.ccic.salessapp.common.core.logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFormatMessage;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.Format;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

public class Logger {
    private org.slf4j.Logger log;

    private Logger(Class clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }



    public void trace(String format, Object... arguments){
       log.trace(message(format, arguments));
    }

    public void debug(String format, Object... arguments){
        log.debug(message(format, arguments));
    }

    public void info(String format, Object... arguments){
        log.info(message(format, arguments));
    }

    public void error(String format, Object... arguments){
        log.error(message(format, arguments));
    }

    public void error(String format,Throwable t, Object... arguments){
        log.error(message(format, arguments),t);
    }























    private String message(String format, Object... arguments){
        return new MessageFormatted("["+LocalLogger.get()+"] " +format,arguments).getFormattedMessage();
    }


    public final static class LocalLogger{
        private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
        public static void set(String body){
            threadLocal.set(body);
        }
        public static String get(){
            String uid=threadLocal.get();
            if(StringUtils.isBlank(uid)){
                uid=UUID.randomUUID().toString().replaceAll("-","");
                set(uid);
            }
            return uid;
        }
        public static void remove(){
            threadLocal.remove();
        }
    }


    final static class MessageFormatted implements Serializable {

        private static final int HASHVAL = 31;
        private static final String FORMAT_SPECIFIER = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
        private static final Pattern MSG_PATTERN = Pattern.compile(FORMAT_SPECIFIER);
        private static final long serialVersionUID = 1830617624981288010L;

        private String messagePattern;
        private transient Object[] argArray;
        private String[] stringArgs;
        private transient String formattedMessage;
        private final Throwable throwable;
        private Message message;
        private final Locale locale;

        /**
         * Constructs with a locale, a pattern and a single parameter.
         *
         * @param locale         The locale
         * @param messagePattern The message pattern.
         * @param arg            The parameter.
         * @since 2.6
         */
        public MessageFormatted(final Locale locale, final String messagePattern, final Object arg) {
            this(locale, messagePattern, new Object[]{arg}, null);
        }

        /**
         * Constructs with a locale, a pattern and two parameters.
         *
         * @param locale         The locale
         * @param messagePattern The message pattern.
         * @param arg1           The first parameter.
         * @param arg2           The second parameter.
         * @since 2.6
         */
        public MessageFormatted(final Locale locale, final String messagePattern, final Object arg1, final Object arg2) {
            this(locale, messagePattern, new Object[]{arg1, arg2});
        }

        /**
         * Constructs with a locale, a pattern and a parameter array.
         *
         * @param locale         The locale
         * @param messagePattern The message pattern.
         * @param arguments      The parameter.
         * @since 2.6
         */
        public MessageFormatted(final Locale locale, final String messagePattern, final Object... arguments) {
            this(locale, messagePattern, arguments, null);
        }

        /**
         * Constructs with a locale, a pattern, a parameter array, and a throwable.
         *
         * @param locale         The Locale
         * @param messagePattern The message pattern.
         * @param arguments      The parameter.
         * @param throwable      The throwable
         * @since 2.6
         */
        public MessageFormatted(final Locale locale, final String messagePattern, final Object[] arguments, final Throwable throwable) {
            this.locale = locale;
            this.messagePattern = messagePattern;
            this.argArray = arguments;
            this.throwable = throwable;
        }

        /**
         * Constructs with a pattern and a single parameter.
         *
         * @param messagePattern The message pattern.
         * @param arg            The parameter.
         */
        public MessageFormatted(final String messagePattern, final Object arg) {
            this(messagePattern, new Object[]{arg}, null);
        }

        /**
         * Constructs with a pattern and two parameters.
         *
         * @param messagePattern The message pattern.
         * @param arg1           The first parameter.
         * @param arg2           The second parameter.
         */
        public MessageFormatted(final String messagePattern, final Object arg1, final Object arg2) {
            this(messagePattern, new Object[]{arg1, arg2});
        }

        /**
         * Constructs with a pattern and a parameter array.
         *
         * @param messagePattern The message pattern.
         * @param arguments      The parameter.
         */
        public MessageFormatted(final String messagePattern, final Object... arguments) {
            this(messagePattern, arguments, null);
        }

        /**
         * Constructs with a pattern, a parameter array, and a throwable.
         *
         * @param messagePattern The message pattern.
         * @param arguments      The parameter.
         * @param throwable      The throwable
         */
        public MessageFormatted(final String messagePattern, final Object[] arguments, final Throwable throwable) {
            this.locale = Locale.getDefault(Locale.Category.FORMAT);
            this.messagePattern = messagePattern;
            this.argArray = arguments;
            this.throwable = throwable;
        }


        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final MessageFormatted that = (MessageFormatted) o;

            if (messagePattern != null ? !messagePattern.equals(that.messagePattern) : that.messagePattern != null) {
                return false;
            }
            if (!Arrays.equals(stringArgs, that.stringArgs)) {
                return false;
            }

            return true;
        }

        /**
         * Gets the message pattern.
         *
         * @return the message pattern.
         */
        public String getFormat() {
            return messagePattern;
        }

        /**
         * Gets the formatted message.
         *
         * @return the formatted message.
         */
        public String getFormattedMessage() {
            if (formattedMessage == null) {
                if (message == null) {
                    message = getMessage(messagePattern, argArray, throwable);
                }
                formattedMessage = message.getFormattedMessage();
            }
            return formattedMessage;
        }

        protected Message getMessage(final String msgPattern, final Object[] args, final Throwable aThrowable) {
            try {
                final java.text.MessageFormat format = new java.text.MessageFormat(msgPattern);
                final Format[] formats = format.getFormats();
                if (formats != null && formats.length > 0) {
                    return new MessageFormatMessage(locale, msgPattern, args);
                }
            } catch (final Exception ignored) {
                // Obviously, the message is not a proper pattern for MessageFormat.
            }
            try {
                if (MSG_PATTERN.matcher(msgPattern).find()) {
                    return new StringFormattedMessage(locale, msgPattern, args);
                }
            } catch (final Exception ignored) {
                // Also not properly formatted.
            }
            return new ParameterizedMessage(msgPattern, args, aThrowable);
        }

        /**
         * Gets the message parameters.
         *
         * @return the message parameters.
         */
        public Object[] getParameters() {
            if (argArray != null) {
                return argArray;
            }
            return stringArgs;
        }

        public Throwable getThrowable() {
            if (throwable != null) {
                return throwable;
            }
            if (message == null) {
                message = getMessage(messagePattern, argArray, null);
            }
            return message.getThrowable();
        }


        @Override
        public int hashCode() {
            int result = messagePattern != null ? messagePattern.hashCode() : 0;
            result = HASHVAL * result + (stringArgs != null ? Arrays.hashCode(stringArgs) : 0);
            return result;
        }

        private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            formattedMessage = in.readUTF();
            messagePattern = in.readUTF();
            final int length = in.readInt();
            stringArgs = new String[length];
            for (int i = 0; i < length; ++i) {
                stringArgs[i] = in.readUTF();
            }
        }

        @Override
        public String toString() {
            return getFormattedMessage();
        }

        private void writeObject(final ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            getFormattedMessage();
            out.writeUTF(formattedMessage);
            out.writeUTF(messagePattern);
            out.writeInt(argArray.length);
            stringArgs = new String[argArray.length];
            int i = 0;
            for (final Object obj : argArray) {
                final String string = String.valueOf(obj);
                stringArgs[i] = string;
                out.writeUTF(string);
                ++i;
            }
        }

    }
}
