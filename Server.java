public class Server
{
    public static void main(final String[] args)
    {
	final var route = org.springframework.web.reactive.function.server.RouterFunctions.route(org.springframework.web.reactive.function.server.RequestPredicates.GET("/"), request -> org.springframework.web.reactive.function.server.ServerResponse.ok().body(org.springframework.web.reactive.function.BodyInserters.fromObject("Hello")));
        final var httpHandler = org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler(route);
	final var adapter = new org.springframework.http.server.reactive.ReactorHttpHandlerAdapter(handler);
        reactor.ipc.netty.http.server.HttpServer.create().port(80).handle(adapter).bind().block();
    }
}
