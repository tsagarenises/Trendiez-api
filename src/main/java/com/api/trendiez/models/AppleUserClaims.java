package com.api.trendiez.models;

public class AppleUserClaims {
//    The issuer registered claim identifies the principal that issues the identity token. Because Apple generates the token, the value is https://appleid.apple.com.
    String iss;


//    The subject registered claim identifies the principal that’s the subject of the identity token. Because this token is for your app, the value is the unique identifier for the user.
    String sub;

//    The audience registered claim identifies the recipient of the identity token. Because the token is for your app, the value is the client_id from your developer account.
    String aud;

//    The issued at registered claim indicates the time that Apple issues the identity token, in the number of seconds since the Unix epoch in UTC.
    String iat;

//    The expiration time registered claim identifies the time that the identity token expires, in the number of seconds since the Unix epoch in UTC. The value must be greater than the current date and time when verifying the token
    String exp;

//    A string for associating a client session with the identity token. This value mitigates replay attacks and is present only if you pass it in the authorization request.
    String nonce;

    Boolean nonce_supported;
    String email;
    Boolean email_verified;

//    A string or Boolean value that indicates whether the email that the user shares is the proxy address. The value can either be a string ("true" or "false") or a Boolean (true or false).
    Boolean is_private_email;

//    An Integer value that indicates whether the user appears to be a real person. Use the value of this claim to mitigate fraud. The possible values are: 0 (or Unsupported), 1 (or Unknown), 2 (or LikelyReal). For more information, see ASUserDetectionStatus. This claim is present only in iOS 14 and later, macOS 11 and later, watchOS 7 and later, tvOS 14 and later. The claim isn’t present or supported for web-based apps.
    int real_user_status;

//    A string value that represents the transfer identifier for migrating users to your team. This claim is present only during the 60-day transfer period after you transfer an app.
    String transfer_sub;

}
