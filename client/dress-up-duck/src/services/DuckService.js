const url = "http://localhost:8080/api/duck";

export async function findAll() {
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject("Could not find ducks");
}