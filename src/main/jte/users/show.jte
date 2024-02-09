@import org.example.hexlet.dto.users.UserPage
@import org.example.hexlet.utils.NamedRoutes
@param UserPage page


@template.layout.page(content = @`
    <main>
        <h1>${page.getUser().getName()}</h1>
        <p>${page.getUser().getEmail()}</p>
    </main>
    <a href="${NamedRoutes.editUserPath(page.getUser().getId())}">Update user</a>
`)


