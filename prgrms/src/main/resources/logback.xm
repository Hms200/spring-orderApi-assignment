<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter"/>
  <conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>
  <conversionRule conversionWord="wEx" converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter"/>

  <property name="console_log" value="%clr(%d{yyyy-MM-dd HH:mm:ss.SSS,IST}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}:%L){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}"/>

  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <Pattern>${console_log}</Pattern>
    </encoder>
  </appender>

  <root level="INFO">
    <appender-ref ref="console"/>
  </root>
</configuration>