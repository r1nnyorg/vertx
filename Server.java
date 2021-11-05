public class Server
{
    public static void main(final java.lang.String[] args)
    {
        final var connectionFactory = new io.r2dbc.postgresql.PostgresqlConnectionFactory(io.r2dbc.postgresql.PostgresqlConnectionConfiguration.builder().host("postgrespostgres.postgres.database.azure.com").username("postgres").password("pos1gres+").database("default").enableSsl().build());
	final var client = org.springframework.r2dbc.core.DatabaseClient.create(connectionFactory);
	final var haha = new org.springframework.core.ParameterizedTypeReference<java.util.Map<java.lang.String, java.lang.Object>>(){};
	final var route = org.springframework.web.reactive.function.server.RouterFunctions.route(org.springframework.web.reactive.function.server.RequestPredicates.GET("/"), request -> org.springframework.web.reactive.function.server.ServerResponse.ok().body(client.sql("select * from productItem").fetch().all(), haha));
        final var httpHandler = org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler(route);
	final var adapter = new org.springframework.http.server.reactive.ReactorHttpHandlerAdapter(httpHandler);
        final var server = reactor.netty.http.server.HttpServer.create().port(80).handle(adapter).bindNow();
	server.onDispose().block();
    }
}
