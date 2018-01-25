import ch.qos.logback.classic.encoder.PatternLayoutEncoder

scan("10 seconds")
def defaultPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5p %t \\(%F:%L\\) - %msg%n"
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = defaultPattern
    }
}
logger("com.nevermore", DEBUG, ["STDOUT"], false)
root(DEBUG, ["STDOUT"])
