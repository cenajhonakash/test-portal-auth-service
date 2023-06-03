package com.testportal.authservice.constants;

public enum AppConstants {
	AUTHORIZATION("Authorization"), BEARER("Bearer"), EXCH_REQ_URI("exchangeRequestUri"), EXCH_REQ_METHOD("exchangeRequestMethod"),
	PVT_KEY("-----BEGIN RSA PRIVATE KEY-----\r\n" + "MIIEpAIBAAKCAQEAwmrqGq189Bv5gJJB8HUH8R/PPNwRLsG9K7+5niersvHNhmpw\r\n"
			+ "4AjJMNmbHIEjfMWhs40KId3PhOuZOfIvHir2vElTrlpFh1YQJb7pl9VL3ieSeomF\r\n" + "MTVP23fp5mKiigowjQ9nCKO+oxzOU7k2YNn+p2L6QXlaCGSqPDEWCr4C6G10x/sf\r\n"
			+ "dIZnynSR3T75ryQnjlZV7eBkQp8S8h/mVEJwNRSGKPQIV+LBNQ+lF7EeXV9CekJP\r\n" + "C9zHrxQTMScwtjrZNxTLFmSqvwQ0zwh0BSiNsiuyildOJinVjoVxcS49Wg0hYBwn\r\n"
			+ "DJHVSMk+rPuibqRkQ7DwO+r6T8iW9uJVb4F9fQIDAQABAoIBAQCeuWzRROxr217y\r\n" + "kfXVm6ZVPJQT6tL4amHIEuo27cpm+LTD1ai7r1CaWj4homjXl/4xOaeZCJ9AtJxP\r\n"
			+ "S6MeLRNtmyv/f2GeYSwjtN6+//vFrmcPNbERAKZm1lqWpdZvfmwu7fE/g3fDcWQd\r\n" + "xGtZ3P/jppIL3Egd7BlztNt6/7AeErk0Stj7mADkm0S4ynzB/P4Mc408ZGsRFQmQ\r\n"
			+ "JueNCAxefbQRO29eqMbktmDbBYlOugzIPRuH5m8+u8YsROLioHdQTRDchIyMXhGe\r\n" + "L0l1MPLoyZQhkH/9lTdW96B1cyAQgD0hZV1zm9hnfZuXu3QbJV+Yi92WUN0f1h8t\r\n"
			+ "d9nPp2VdAoGBAPIQLPgk/YWGVFSpunJa2H06WsaShSD5prnhZlhMHIftbtI//JWE\r\n" + "b1LPRIjql0P057yOJltvj9MdhTWajGm6qbFA7OwiG20MFQ4xhd0tEnVaOAIr+uvG\r\n"
			+ "ILjnJtd+i9SWMAeEcba+6Kljh/Nfwujg4E5oSw5degsgILLHPOGBk+hrAoGBAM2c\r\n" + "eu3NbpNnRxG+EHiDjeOJD2TGMYeEDMK965kuJP3Xa1tQXADrJzIQ90ddZk86Q9yD\r\n"
			+ "OfxSwbaWjF5Tyst2oUkHbl3ON8Wpz9EKn5SxiiUjizonobD7HiofHmAp79JUWjNu\r\n" + "+fgSP8CYMH/zIw0lvd8VU+Q9v4sONJxz3DYUzUu3AoGBAKPngAumQGQhSQjqxGya\r\n"
			+ "RuzEjqLWo35ojRRgI0S2hLslcly7JvS28ApKfkWXU0WYlzF9r0tKh0CURJa/R0Rw\r\n" + "OGblZ4ecsKVuAPPTZKkW0+DSbgpKbWUoiw+otQxFi7ku2Oa+L6FmM3Q/CNcqiHfX\r\n"
			+ "jQqKZAgeE31lC78CWZ74doZxAoGAF0dsM5hauJ2s0jluq/ZZDjXqZEJ8QReWSkYu\r\n" + "cn1i+sp1ATWr9P6qxWReWtocR0QVgtXorsS/4ehRpCxgfYDE6fvJZx19+wcq3EQT\r\n"
			+ "pUYkmefYT5FFIUCNPb+gNMsKuAmM3N3nt0p5pViVxSbGBSsMwEEt6l2DXj8RfdUw\r\n" + "TX81bykCgYACD7XfpbgZ64Rd5AaZkbPFZ4K0FjrS3GjvXJed0cM7qqAXO2nn2VZ6\r\n"
			+ "fzCiG8ZyHTTJElBHdbRNhG6S/+me2M6meO9q7ObPTEUVjaASIe7j5Fs4/2bJEjr/\r\n" + "hlprDvtfWKMRXu46DfIQL/sHuCPN+TQFN+88muIJ0FV6a3FKrx9MBg==\r\n" + "-----END RSA PRIVATE KEY-----"),
	PUBLIC_KEY("-----BEGIN PUBLIC KEY-----\r\n" + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwmrqGq189Bv5gJJB8HUH\r\n"
			+ "8R/PPNwRLsG9K7+5niersvHNhmpw4AjJMNmbHIEjfMWhs40KId3PhOuZOfIvHir2\r\n" + "vElTrlpFh1YQJb7pl9VL3ieSeomFMTVP23fp5mKiigowjQ9nCKO+oxzOU7k2YNn+\r\n"
			+ "p2L6QXlaCGSqPDEWCr4C6G10x/sfdIZnynSR3T75ryQnjlZV7eBkQp8S8h/mVEJw\r\n" + "NRSGKPQIV+LBNQ+lF7EeXV9CekJPC9zHrxQTMScwtjrZNxTLFmSqvwQ0zwh0BSiN\r\n"
			+ "siuyildOJinVjoVxcS49Wg0hYBwnDJHVSMk+rPuibqRkQ7DwO+r6T8iW9uJVb4F9\r\n" + "fQIDAQAB\r\n" + "-----END PUBLIC KEY-----");

	public final String value;

	private AppConstants(String value) {
		this.value = value;
	}

}
