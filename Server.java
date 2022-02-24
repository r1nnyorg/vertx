public class Server
{
    public static void main(final java.lang.String[] args)
    {
        //final var connectionFactory = new io.r2dbc.postgresql.PostgresqlConnectionFactory(io.r2dbc.postgresql.PostgresqlConnectionConfiguration.builder().host("postgrespostgres.postgres.database.azure.com").username("postgres").password("pos1gres+").database("default").enableSsl().build());
	//final var client = org.springframework.r2dbc.core.DatabaseClient.create(connectionFactory);
	final var objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	/*final var route = org.springframework.web.reactive.function.server.RouterFunctions.route().POST("/ajax", request -> org.springframework.web.reactive.function.server.ServerResponse.ok().body(request.bodyToFlux(java.lang.String.class).flatMap(body ->
{
    try
    {
        final java.util.Map<String, Object> a = objectMapper.readValue(body, java.util.Map.class);
	return client.sql("select * from" + a.entrySet().stream().map(entry -> java.lang.String.join(" ", entry.getKey(), entry.getValue().toString())).collect(java.util.stream.Collectors.joining(" "))).fetch().all();
    }
    catch (final java.lang.Exception e)
    {
	return reactor.core.publisher.Flux.empty();
    }
}), java.util.Map.class)).build();
        final var httpHandler = org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler(route);
	final var adapter = new org.springframework.http.server.reactive.ReactorHttpHandlerAdapter(httpHandler);*/
        final var server = reactor.netty.http.server.HttpServer.create().port(80).route(routes -> routes.post("/ajax", (request, response) -> response.sendString( reactor.core.publisher.Flux.just(java.util.Map.of("0", 0, "1", 1), java.util.Map.of("2", 2, "3", 3)).map( objectMapper::writeValueAsString ) ))).bindNow();
	server.onDispose().block();
    }
}
