@import gg.jte.Content
@import org.example.hexlet.utils.NamedRoutes
@param Content content


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<header>
    <a href="${NamedRoutes.rootPath()}">Home</a>
    <a href="${NamedRoutes.usersPath()}">Users</a>
    <a href="${NamedRoutes.coursesPath()}">Courses</a>
</header>
${content}
<footer>
    <a href="https://github.com/a88217">Github</a>
</footer>
</body>
</html>
