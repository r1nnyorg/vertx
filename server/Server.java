package server;

@org.springframework.web.bind.annotation.RestController
@org.springframework.boot.autoconfigure.SpringBootApplication
public class Server
{
    @org.springframework.web.bind.annotation.RequestMapping("/")
    public String index()
    {
        return "Hello Spring Boot";
    }
    public static void main(final String[] args)
    {
        org.springframework.boot.SpringApplication.run(Server.class, args);
	app.setDefaultProperties(java.util.Collections.singletonMap("server.port", "80"));
        app.run(args);
    }
}
