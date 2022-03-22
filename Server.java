public class Server
{
    public static void main(final java.lang.String[] args)
    {
        //java.lang.System.setProperty("java.net.preferIPv6Addresses", "true");
        final var vertx = io.vertx.core.Vertx.vertx();
        final var router = io.vertx.ext.web.Router.router(vertx);
        router.route().handler(io.vertx.ext.web.handler.CorsHandler.create()).handler(io.vertx.ext.web.handler.BodyHandler.create());
        final var client = io.vertx.redis.client.Redis.createClient(vertx, new io.vertx.redis.client.RedisOptions().setType(io.vertx.redis.client.RedisClientType.CLUSTER).addConnectionString("redis://redis").setPassword(java.lang.System.getenv("password"))); //final var client = io.vertx.redis.client.Redis.createClient(vertx, "redis://redis");
        final var redis = io.vertx.redis.client.RedisAPI.api(client);
        //final var database = io.vertx.pgclient.PgPool.pool(vertx, new io.vertx.pgclient.PgConnectOptions().setHost("postgrespostgres.postgres.database.azure.com").setDatabase("default").setUser("postgres").setPassword("pos1gres+").setSsl(true).setPemTrustOptions(new io.vertx.core.net.PemTrustOptions().addCertPath("DigiCertGlobalRootCA.crt.pem")), new io.vertx.sqlclient.PoolOptions());
        final var database = io.vertx.pgclient.PgPool.pool(vertx, new io.vertx.pgclient.PgConnectOptions().setHost("cockroach").setDatabase("defaultdb").setUser("root").setSslMode(io.vertx.pgclient.SslMode.VERIFY_FULL).setPort(26257), new io.vertx.sqlclient.PoolOptions());
        router.post("/ajax").respond(ctx ->
{
    final var body = ctx.getBodyAsJson().stream().map($ -> java.lang.String.join(" ", $.getKey(), $.getValue().toString())).collect(java.util.stream.Collectors.joining(" "));
    return redis.get(body).compose(records -> java.util.Objects.isNull(records) ? database.query("select * from" + body).mapping($ -> $.toJson()).execute().map(rowset -> java.util.stream.StreamSupport.stream(rowset.spliterator(), false).collect(java.util.stream.Collector.of(io.vertx.core.json.JsonArray::new, io.vertx.core.json.JsonArray::add, io.vertx.core.json.JsonArray::add))).onSuccess($ -> redis.set(java.util.List.of(body, $.toString()))) : io.vertx.core.Future.succeededFuture(new io.vertx.core.json.JsonArray(records.toBuffer())));
});  
        vertx.createHttpServer().requestHandler(router).listen(80);
    }
}
