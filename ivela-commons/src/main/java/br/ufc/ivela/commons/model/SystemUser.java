/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "system_user")
@NamedQueries({@NamedQuery(name = "SystemUser.findById", query = "SELECT s FROM SystemUser s WHERE s.id = :id"), @NamedQuery(name = "SystemUser.findByEmail", query = "SELECT s FROM SystemUser s WHERE s.email = :email"), @NamedQuery(name = "SystemUser.findBySocialNumber", query = "SELECT s FROM SystemUser s WHERE s.socialNumber = :socialNumber"), @NamedQuery(name = "SystemUser.findByUsername", query = "SELECT s FROM SystemUser s WHERE s.username = :username"), @NamedQuery(name = "SystemUser.findByPassword", query = "SELECT s FROM SystemUser s WHERE s.password = :password"), @NamedQuery(name = "SystemUser.findByEnabled", query = "SELECT s FROM SystemUser s WHERE s.enabled = :enabled")})
public class SystemUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_system_user", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "social_number", nullable = false)
    private String socialNumber;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
            
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name="profile")
    private Long profileId;    
    @Transient
    private Profile profile;
    
    @JoinColumn(name = "authentication", referencedColumnName = "id")
    @ManyToOne
    private Authentication authentication;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "system_user_functionality",
    joinColumns = @JoinColumn(name = "system_user"),
    inverseJoinColumns = @JoinColumn(name = "functionality"))
    private Set<Functionality> functionalities;

    @Transient
    private GrantedAuthority[] authorities;
        
    @Column(name="last_unit_content")
    private Long lastUnitContentId;
    
    public SystemUser() {
    }

    public SystemUser(Long id) {
        this.id = id;
    }

    public SystemUser(Long id, String email, String socialNumber, String username, String password, boolean enabled) {
        this.id = id;
        this.email = email;
        this.socialNumber = socialNumber;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.SystemUser[id=" + id + "]";
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
}

    public void setAuthorities(GrantedAuthority[] authorities) {
        this.authorities = authorities;
    }
    
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    public boolean isEnabled() {
        return getEnabled();
    }

    public Collection<Functionality> getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(Set<Functionality> functionalities) {
        this.functionalities = functionalities;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getLastUnitContentId() {
        return lastUnitContentId;
    }

    public void setLastUnitContentId(Long lastUnitContentId) {
        this.lastUnitContentId = lastUnitContentId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
