const url = "http://localhost:8080/api/duck";

export async function findDuck(duckId) {
    const response = await fetch(`${url}/${duckId}`)
    if (response.ok) {
        return response.json();
    }
    return Promise.reject(`Could not find duck id: ${duckId}`)
}

export async function findAll() {
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject("Could not find ducks");
}