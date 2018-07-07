package com.github.dizzarg.simple.http.server.servlets;

import com.github.dizzarg.simple.http.server.auth.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SignInServletTest {

    private static final String TEST_LOGIN = "testLogin";
    private static final String TEST_PASSWORD = "testPassword";
    private SignInServlet signInServlet;
    private AccountService accountService;

    @Before
    public void setUp() {
        accountService = Mockito.mock(AccountService.class);
        signInServlet = new SignInServlet(accountService);
    }

    @Test
    public void signIn_authorisedLogin_OK() throws IOException {
        when(accountService.signin(eq(TEST_LOGIN), eq(TEST_PASSWORD)))
                .thenReturn(Boolean.TRUE);
        HttpServletRequest req = mockHttpServletRequest();
        HttpServletResponse res = mockHttpServletResponse();

        signInServlet.doPost(req, res);

        verify(req).getParameter(eq(HttpConstants.LOGIN_PARAM));
        verify(req).getParameter(eq(HttpConstants.PASSWORD_PARAM));
        verify(accountService).signin(eq(TEST_LOGIN), eq(TEST_PASSWORD));
        verify(res).setStatus(eq(HttpServletResponse.SC_OK));
        verify(res).setContentType(eq(HttpConstants.CONTENT_TYPE));
    }

    @Test
    public void signIn_unknownLogin_unauthorised() throws IOException {
        when(accountService.signin(eq(TEST_LOGIN), eq(TEST_PASSWORD)))
                .thenReturn(Boolean.FALSE);
        HttpServletRequest req = mockHttpServletRequest();
        HttpServletResponse res = mockHttpServletResponse();

        signInServlet.doPost(req, res);

        verify(req).getParameter(eq(HttpConstants.LOGIN_PARAM));
        verify(req).getParameter(eq(HttpConstants.PASSWORD_PARAM));
        verify(accountService).signin(eq(TEST_LOGIN), eq(TEST_PASSWORD));
        verify(res).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
        verify(res).setContentType(eq(HttpConstants.CONTENT_TYPE));
    }

    @Test
    public void signIn_emptyParameters_unauthorised() throws IOException {
        when(accountService.signin(eq(TEST_LOGIN), eq(TEST_PASSWORD)))
                .thenReturn(Boolean.TRUE);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mockHttpServletResponse();

        signInServlet.doPost(req, res);

        verify(req).getParameter(eq(HttpConstants.LOGIN_PARAM));
        verify(req).getParameter(eq(HttpConstants.PASSWORD_PARAM));
        verify(accountService, never()).signin(anyString(), anyString());
        verify(res).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
        verify(res).setContentType(eq(HttpConstants.CONTENT_TYPE));
    }

    private HttpServletResponse mockHttpServletResponse() throws IOException {
        HttpServletResponse res = mock(HttpServletResponse.class);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(pw);
        return res;
    }

    private HttpServletRequest mockHttpServletRequest() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter(HttpConstants.LOGIN_PARAM)).thenReturn(TEST_LOGIN);
        when(req.getParameter(HttpConstants.PASSWORD_PARAM)).thenReturn(TEST_PASSWORD);
        return req;
    }

}