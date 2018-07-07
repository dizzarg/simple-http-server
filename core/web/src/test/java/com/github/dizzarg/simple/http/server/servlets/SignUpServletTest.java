package com.github.dizzarg.simple.http.server.servlets;

import com.github.dizzarg.simple.http.server.auth.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class SignUpServletTest {

    private static final String TEST_LOGIN = "testLogin";
    private static final String TEST_PASSWORD = "testPassword";
    private SignUpServlet signUpServlet;
    private AccountService accountService;

    @Before
    public void setUp() {
        accountService = Mockito.mock(AccountService.class);
        signUpServlet = new SignUpServlet(accountService);
    }

    @Test
    public void signUp_newLogin_OK() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter(HttpConstants.LOGIN_PARAM)).thenReturn(TEST_LOGIN);
        when(req.getParameter(HttpConstants.PASSWORD_PARAM)).thenReturn(TEST_PASSWORD);
        HttpServletResponse res = mock(HttpServletResponse.class);

        signUpServlet.doPost(req, res);

        verify(req).getParameter(eq(HttpConstants.LOGIN_PARAM));
        verify(req).getParameter(eq(HttpConstants.PASSWORD_PARAM));
        verify(accountService).signup(eq(TEST_LOGIN), eq(TEST_PASSWORD));
        verify(res).setStatus(eq(HttpServletResponse.SC_OK));
        verify(res).setContentType(eq(HttpConstants.CONTENT_TYPE));
    }

}