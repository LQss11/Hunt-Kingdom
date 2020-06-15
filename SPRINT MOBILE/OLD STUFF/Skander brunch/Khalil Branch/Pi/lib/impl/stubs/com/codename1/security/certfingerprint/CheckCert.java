package com.codename1.security.certfingerprint;


/**
 *  Checks the certificate fingerprint for the given server URL
 */
public class CheckCert {

	public CheckCert() {
	}

	/**
	 *  Returns true if this functionality is supported on this OS
	 */
	public static boolean isCertCheckingSupported() {
	}

	/**
	 *  Queries the server for the certificate and validates it, returns the fingerprint
	 */
	public static String getFingerprint(String url) {
	}

	/**
	 *  Queries the server for the certificate and validates it, returns the fingerprint via a callback.
	 *  The method returns immediately and preforms the query asynchronously
	 */
	public static void getFingerprint(String url, com.codename1.util.SuccessCallback callback, com.codename1.util.FailureCallback err) {
	}
}
