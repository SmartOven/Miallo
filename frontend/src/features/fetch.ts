import {backendUri} from "./constants.ts";

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
        // credentials: "same-origin",
        headers: [["Content-Type", "application/json"]],
        method,
    });
}
