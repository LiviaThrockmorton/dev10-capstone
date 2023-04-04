const url = "http://localhost:8080/api/outfit";

export async function findAll() {
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject("Could not find outfits");
}

export async function findById(outfitId) {
    const response = await fetch(`${url}/${outfitId}`);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject(`Could not find outfit with id: ${outfitId}`);
}

export async function findAll() {
    const response = await fetch(url);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject("Could not find outfit");
}