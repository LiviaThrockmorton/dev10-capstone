const url = "http://localhost:8080/api/item";

export async function findByType(itemType) {
    const response = await fetch(`${url}/type/${itemType}`);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject(`Could not find clothing items of type: ${itemType}`);
}