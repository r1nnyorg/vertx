public class Server
{
    public static void main(final java.lang.String[] args)
    {
        io.vertx.core.vertx().createHttpServer().requestHandler(request -> request.response().end("fuck")).listen(80);
    }
}
