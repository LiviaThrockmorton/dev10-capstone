package learn.capstone.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

    public class AppUser implements UserDetails {

        private int appUserId;
        private String username;
        private String password;
        private ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<UserOutfit> getOutfits() {
            return outfits;
        }

        public void setOutfits(List<UserOutfit> outfits) {
            this.outfits = outfits;
        }

        private String email;
        private List<UserOutfit> outfits = new ArrayList<>();

        public AppUser() {
        }

        public AppUser(String username, String password, Collection<String> authorityNames) {
            this.username = username;
            this.password = password;
            addAuthorities(authorityNames);
        }

        public int getAppUserId() {
            return appUserId;
        }

        public void setAppUserId(int appUserId) {
            this.appUserId = appUserId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public void addAuthorities(Collection<String> authorityNames) {
            authorities.clear();
            for (String name : authorityNames) {
                authorities.add(new SimpleGrantedAuthority(name));
            }
        }
    }
