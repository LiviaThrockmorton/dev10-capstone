const url = "http://localhost:8080";

function convertJwtToUser(jwt) {
    const tokens = jwt.split(".");
    const userJson = atob(tokens[1]);
    const user = JSON.parse(userJson);


    user.authorities = user.authorities.split(",");
    console.log(user);

    user.jwt = jwt;
    user.hasAnyAuthority = function (...authNames) {
        for (const name of authNames) {
            if (this.authorities.includes(name)) {
                return true;
            }
        }
        return false;
    }
    return user;
}

export async function authenticate(credentials) {

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    }

    const response = await fetch(`${url}/authenticate`, config);

    if (response.ok) {
        const json = await response.json();
        return convertJwtToUser(json.jwt_token);
    }

    return Promise.reject();
}

export async function refresh() {

    const config = {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("duckToken")}`
        }
    };

    const response = await fetch(`${url}/refresh_token`, config);
    if (response.ok) {
        const json = await response.json();
        return convertJwtToUser(json.jwt_token);
    }

    return Promise.reject();
}