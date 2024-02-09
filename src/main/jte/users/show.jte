@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <main>
        <h1>${page.getUser().getName()}</h1>
        <p>${page.getUser().getEmail()}</p>
    </main>
`)


