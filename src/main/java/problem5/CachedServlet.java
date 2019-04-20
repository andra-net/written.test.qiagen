package problem5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CachedServlet extends HttpServlet {

    private int cacheHits;
    private int lastId;
    private String lastResult;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String result = null;
        int id = extractFromRequest(request);

        synchronized (this) {
            if ((id == lastId) && (lastResult != null)) {
                result = lastResult;
                cacheHits++;
            }
        }

        if (result != null) {
            encodeIntoResponse(response, result);
        } else {

            result = doTimeConsumingCalculation(id);

            synchronized (this) {
                lastId = id;
                lastResult = result;
            }
        }
    }

    private void encodeIntoResponse(
            HttpServletResponse response,
            String result) {

    }

    private String doTimeConsumingCalculation(int id) {

        return "";
    }

    private int extractFromRequest(HttpServletRequest request) {

        return 0;
    }
}

