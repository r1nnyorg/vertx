public class Server
{
    public static void main(final java.lang.String[] args)
    {
        final var connectionFactory = new io.r2dbc.postgresql.PostgresqlConnectionFactory(io.r2dbc.postgresql.PostgresqlConnectionConfiguration.builder().host("postgrespostgres.postgres.database.azure.com").username("postgres").password("pos1gres+").database("default").enableSsl().build());
	final var client = org.springframework.r2dbc.core.DatabaseClient.create(connectionFactory);
	final var objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
	final var route = org.springframework.web.reactive.function.server.RouterFunctions.route(org.springframework.web.reactive.function.server.RequestPredicates.POST("/ajax"), request -> org.springframework.web.reactive.function.server.ServerResponse.ok().body(request.bodyToFlux(java.lang.String.class).flatMap(body -> client.sql(
{
    try
    {
        final java.util.Map<String, Object> a = objectMapper.readValue(body, java.util.Map.class);
	return a.entrySet().stream().map(entry -> java.lang.String.join(" ", entry.getKey(), entry.getValue().toString())).collect(java.util.stream.Collectors.joining(" "));
        //return org.springframework.web.reactive.function.server.ServerResponse.ok().body(request.bodyToFlux(java.lang.String.class).flatMap(body -> client.sql(objectMapper.readValue(body, new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<java.lang.String, java.lang.String>>(){}).entrySet().stream().map(entry -> java.lang.String.join(" ", entry.getKey(), entry.getValue())).collect(java.util.stream.Collectors.joining(" "))).fetch().all()), java.util.Map.class);
    }
    catch (final java.lang.Exception e)
    {
	return null;
    }
}).fetch().all()), java.util.Map.class));
        final var httpHandler = org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler(route);
	final var adapter = new org.springframework.http.server.reactive.ReactorHttpHandlerAdapter(httpHandler);
        final var server = reactor.netty.http.server.HttpServer.create().port(80).handle(adapter).bindNow();
	server.onDispose().block();
    }
}
