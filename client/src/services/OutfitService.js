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

export async function findByUser(userId) {
    const response = await fetch(`${url}/byUser/${userId}`);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject(`Could not find outfits for user`);
}

export async function hide(outfitId) {
    const response = await fetch(`${url}/${outfitId}`, { 
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${localStorage.getItem('duckToken')}`
        }
    })
    if (response.ok) {
        return;
    }
    return Promise.reject(`Could not find outfit id: ${outfitId}`)
}

async function update(outfit) {
    const config = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem('duckToken')}`
        },
        body: JSON.stringify(outfit)
    };

    const response = await fetch(`${url}/${outfit.outfitId}`, config);
    if (response.ok) {
        return;
    }
    if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    }
    return Promise.reject();
}

async function add(outfit) {

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem('duckToken')}`
        },
        body: JSON.stringify(outfit)
    };

    const response = await fetch(url, config);

    if (response.ok) {
        return;
    }

    if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    }

    return Promise.reject();
}

export async function save(outfit) {
    if (outfit.outfitId) {
        return update(outfit);
    } else {
        return add(outfit);
    }
}