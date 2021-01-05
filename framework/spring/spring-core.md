# Spring

> Version 5.3.2
>
> 출처 https://docs.spring.io/spring-framework/docs/current/reference/html/index.html



## 1. The IoC Container

### 1.1. 스프링 IoC 컨테이너과 Beans 소개

- IoC는 의존성 주입(DI)으로도 알려져 있다. 
- IoC는 객체가 의존성을 생성자 전달인자나, 팩토리 메서드의 전달인자, 또는 생성된 후 객체 인스턴스를 생성할 때, 또는 팩토리 메서드에서 리턴 받을 때 정의하는 과정이다.
- 컨테이너는 그리고나서 빈을 생성할 때 의존성을 주입한다.
- 이 과정은 



- `org.springframework.beans` 와 `org.springframework.context` 패키지는 스프링 프레임워크의 IoC  컨테이너의 기초가 된다. 
- `BeanFactory` 인터페이스는 어떤 타입의 객체든 다룰 수 있는 더 나은 구성 메커니즘을 제공한다.
- `ApplicationContext` 는 `BeanFactory` 의 서브 인터페이스이다.
  - 

