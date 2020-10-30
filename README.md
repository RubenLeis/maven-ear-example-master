maven ear example
=====================
-Jboss 7.3

Issue Description:

In the next servlet we are trying to call and async ejb (AsyncEjbTaskExecutor.java) through jndi.
http://localhost:8080/ServletThree

In this ejb async we return a Future with a boolean after a thread sleep, but when we try to retrieve the ejb we obtain the next error:
java.lang.IllegalStateException: Object does not represent an acutal Future

This case is very similar to this example:  https://tomee.apache.org/examples-trunk/async-methods/README.html

This worked perfectly under Jboss 6


