package com.onlinefoodorder.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.onlinefoodorder.controller.AdminController;
import com.onlinefoodorder.dao.AdminDao;
import com.onlinefoodorder.model.Admin;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.Matchers.containsString;


class AdminControllerTest {

    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @Test
    void testGoToHomeDuringStart() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testGoToAdminPage() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        mockMvc.perform(get("/admindashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    void testGoToAdminLoginPage() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        mockMvc.perform(get("/adminlogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminlogin"));
    }

    @Test
    void testGoToAdminRegisterPage() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        mockMvc.perform(get("/adminregister"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminregister"));
    }

    @Test
    void testRegisterAdminSuccess() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        Admin mockAdmin = new Admin();
        mockAdmin.setFirstname("John");
        when(adminDao.save(any(Admin.class))).thenReturn(mockAdmin);

        mockMvc.perform(post("/adminregister")
                .flashAttr("admin", mockAdmin))
                .andExpect(status().isOk())
                .andExpect(view().name("adminlogin"))
                .andExpect(model().attribute("status", "John Successfully Registered as ADMIN"));
    }

    @Test
    void testRegisterAdminFailure() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        Admin mockAdmin = new Admin();
        mockAdmin.setFirstname("John");
        when(adminDao.save(any(Admin.class))).thenReturn(null);

        mockMvc.perform(post("/adminregister")
                .flashAttr("admin", mockAdmin))
                .andExpect(status().isOk())
                .andExpect(view().name("adminregister"))
                .andExpect(model().attribute("status", "John Failed to Registered as ADMIN"));
    }

    
    @Test
    void testLoginAdminFailure() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setViewResolvers(viewResolver)
                .build();

        when(adminDao.findByEmailidAndPassword(toString(), toString())).thenReturn(null);

        mockMvc.perform(post("/adminlogin")
                .param("emailid", "test@example.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("status", "Failed to login as Admin!"));
    }

}
