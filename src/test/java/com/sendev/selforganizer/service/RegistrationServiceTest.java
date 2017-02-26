package com.sendev.selforganizer.service;

import com.sendev.selforganizer.BaseTest;
import com.sendev.selforganizer.dto.Registration;
import com.sendev.selforganizer.exception.RegistrationException;
import com.sendev.selforganizer.model.Role;
import com.sendev.selforganizer.model.User;
import com.sendev.selforganizer.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static com.sendev.selforganizer.util.ModelBuilder.registration;
import static com.sendev.selforganizer.util.ModelBuilder.user;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RegistrationServiceTestConfig.class})
public class RegistrationServiceTest extends BaseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder encoder;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        Mockito.reset(userRepository);
    }

    @Test(expected = RegistrationException.class)
    public void registerUserExists() {
        when(userRepository.findOne(anyString())).thenReturn(user());
        registrationService.register(registration("user@gmail.com"));
    }

    @Test(expected = RegistrationException.class)
    public void registerPasswordsMismatch() {
        registrationService.register(registration("user@gmail.com", "passwordMismatch"));
    }

    @Test
    public void registerUserSuccess() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        Registration registration = registration("user@gmail.com");
        registrationService.register(registration);
        verify(userRepository).save(captor.capture());
        User actual = captor.getValue();
        assertEquals(registration.getEmail(), actual.getEmail());
        assertEquals(registration.getFirstName(), actual.getFirstName());
        assertEquals(registration.getLastName(), actual.getLastName());
        assertTrue(encoder.matches(registration.getPassword(), actual.getPassword()));
        assertEquals(Role.USER, actual.getRole());
    }

}
