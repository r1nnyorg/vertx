public class Server
{
    public static void main(final java.lang.String[] args)
    {
        java.lang.System.setProperty("java.net.preferIPv6Addresses", "true");
        final var vertx = io.vertx.core.Vertx.vertx();
        final var router = io.vertx.ext.web.Router.router(vertx);
        router.route().handler(io.vertx.ext.web.handler.CorsHandler.create()).handler(io.vertx.ext.web.handler.BodyHandler.create());
        final var client = io.vertx.redis.client.Redis.createClient(vertx, "redis://redis");
        final var redis = io.vertx.redis.client.RedisAPI.api(client);
        final var database = io.vertx.pgclient.PgPool.pool(vertx, new io.vertx.pgclient.PgConnectOptions().setHost("postgrespostgres.postgres.database.azure.com").setDatabase("default").setUser("postgres").setPassword("pos1gres+").setSsl(true).setPemTrustOptions(new io.vertx.core.net.PemTrustOptions().addCertPath("DigiCertGlobalRootCA.crt.pem")), new io.vertx.sqlclient.PoolOptions());
        router.post("/ajax").respond(ctx ->
{
    final var body = ctx.getBodyAsJson().stream().map($ -> java.lang.String.join(" ", $.getKey(), $.getValue().toString())).collect(java.util.stream.Collectors.joining(" "));
    return client.connect().compose(connection -> redis.get(body)).compose(records -> java.util.Objects.isNull(records) ? database.query("select * from" + body).execute().map(rowset -> java.util.stream.StreamSupport.stream(rowset.spliterator(), false).collect(java.util.stream.Collectors.toList())) : records);
});  
        vertx.createHttpServer().requestHandler(router).listen(80);
    }
}
