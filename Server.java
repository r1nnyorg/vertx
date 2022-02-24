public class Server
{
    public static void main(final java.lang.String[] args)
    {
        final var vertx = io.vertx.core.Vertx.vertx();
        final var router = io.vertx.ext.web.Router.router(vertx);
        router.route().handler(io.vertx.ext.web.handler.CorsHandler.create()).handler(io.vertx.ext.web.handler.BodyHandler.create());
        router.post("/ajax").respond(ctx -> io.vertx.core.Future.succeededFuture(ctx.getBodyAsJson().stream().map($ -> java.lang.String.join(" ", $.getKey(), $.getValue().toString())).collect(java.util.stream.Collectors.joining(" "))));  
        vertx.createHttpServer().requestHandler(router).listen(80);
    }
}
