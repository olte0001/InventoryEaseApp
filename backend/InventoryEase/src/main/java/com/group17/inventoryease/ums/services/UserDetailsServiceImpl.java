package com.group17.inventoryease.ums.services;

/*
* "This service will be responsible for loading user-specific data."
* Source: https://medium.com/@bubu.tripathy/role-based-access-control-with-spring-security-ca59d2ce80b0
* */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
    }

    private Set<GrantedAuthority> getAuthorities(Role role) {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    }
}