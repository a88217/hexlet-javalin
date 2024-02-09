@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <a href="/users/build">New User</a>
    @for(var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getName()}</a></h2>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
`)
