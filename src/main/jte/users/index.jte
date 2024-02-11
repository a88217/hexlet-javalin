@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.utils.NamedRoutes
@param UsersPage page

@template.layout.page(
page = page,
content = @`

    <a href="${NamedRoutes.buildUserPath()}">New User</a>
    @for(var user : page.getUsers())
        <div>
            <h2><a href="${NamedRoutes.usersPath()}/${user.getId()}">${user.getName()}</a></h2>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
`)
