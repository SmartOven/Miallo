let backendUri = ''

if (window.location.hostname === 'localhost') {
    backendUri = 'http://localhost:8080';
}

export enum RequestMethod {
    GET = "GET",
    POST = "POST",
    PUT = "PUT",
    DELETE = "DELETE",
}

export function executeFetch(
    uri: string,
    method: RequestMethod,
    body: any = null,
): Promise<Response> {
    return fetch(backendUri + uri, {
        body: body === null ? null : JSON.stringify(body),
        headers: [["Content-Type", "application/json"]],
        method,
    });
}
