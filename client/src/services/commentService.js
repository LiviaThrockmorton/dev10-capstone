const url = "http://localhost:8080/api/comment";

export async function findByOutfit(outfitId) {
    const response = await fetch(`${url}/outfitComments/${outfitId}`);
    if (response.ok) {
        return response.json();
    }
    return Promise.reject("No comments yet. Be the first!");
}

export async function findByHidden() {
    const response = await fetch(`${url}/admin`)
    if (response.ok) {
        return response.json();
    }
    return Promise.reject(`No comments here.`)
}

export async function deleteById(commentId) {
    const response = await fetch(`${url}/${commentId}`, { method: "DELETE" })
    if (response.ok) {
        return;
    }
    return Promise.reject(`Could not find comment id: ${commentId}`)
}

async function update(comment) {

    const config = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(comment)
    };

    const response = await fetch(`${url}/${comment.commentId}`, config);

    if (response.ok) {
        return;
    }

    if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    }

    return Promise.reject();
}

async function add(comment) {

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem('duckToken')}`
        },
        body: JSON.stringify(comment)
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

export async function save(comment) {
    if (comment.Id) {
        return update(comment);
    } else {
        return add(comment);
    }
}