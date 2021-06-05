
package ap.test;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import ap.global.gl;
import ap.utils.Bau;

public class Encryption {

	public Encryption() {
	}

	public static byte[] generateSecurityKey() throws Exception {
		
		KeyGenerator kg = KeyGenerator.getInstance("RC4");

		kg.init(40);

		SecretKey key = kg.generateKey();

		return key.getEncoded();
	}

	public static byte[] symmEncrypt(byte clearText[], byte key[]) throws Exception {
		Cipher cipher = Cipher.getInstance("RC4");

		cipher.init(1, new SecretKeySpec(key, "RC4"));

		return cipher.doFinal(clearText);
	}

	public static byte[] generateSecurityKeyAES() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("AES");

		kg.init(128);

		SecretKey key = kg.generateKey();

		return key.getEncoded();
	}

	public static byte[] symmEncryptAES(byte clearText[], byte key[]) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(1, new SecretKeySpec(key, "AES"));

		return cipher.doFinal(clearText);
	}

	public static byte[] asymmEncrypt(byte clearText[], byte certs[]) throws Exception {
		Certificate cert = getCertificate(certs);

		return asymmEncrypt(clearText, ((Key) (cert.getPublicKey())));
	}

	public static void validateCertificate(byte certs[]) throws Exception {
		Certificate cert = getCertificate(certs);

		KeyStore trustKeyStore = getTrustKeyStore();

		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		List list = new ArrayList();

		list.add(cert);

		java.security.cert.CertPath path = cf.generateCertPath(list);

		CertPathValidator validator = CertPathValidator.getInstance("PKIX");

		PKIXParameters params = new PKIXParameters(trustKeyStore);

		params.setRevocationEnabled(false);

		validator.validate(path, params);
	}

	public static void validateHostname(String hostname, byte certs[]) throws Exception {
		Certificate cert = getCertificate(certs);

		if (hostname != null && hostname.trim().equalsIgnoreCase("localhost"))
			return;
		try {
			InetAddress.getByName(hostname);
		} catch (UnknownHostException uhe) {
			throw new UnknownHostException((new StringBuilder()).append("Could not resolve CIS server hostname: ")
					.append(hostname).toString());
		}
		if (cert instanceof X509Certificate) {
			X509Certificate x509cert = (X509Certificate) cert;

			String dn = x509cert.getSubjectDN().getName();

			String cn = getCN(dn);

			if (!hostname.equalsIgnoreCase(cn))
				throw new Exception((new StringBuilder()).append("hostname invalid: expected '").append(hostname)
						.append("', received '").append(cn).append("'").toString());
			else
				return;
		} else {
			throw new Exception((new StringBuilder()).append("The certificate (").append(cert.getClass())
					.append(") is not instanceof X509Certificate").toString());
		}
	}

	public static Certificate getCertificate(byte certBytes[]) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(certBytes);

		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		return cf.generateCertificate(bais);
	}

	private static byte[] asymmEncrypt(byte clearText[], Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");

		cipher.init(1, key);

		return cipher.doFinal(clearText);
	}

	private static String getCN(String dn) {
		int i = 0;

		i = dn.indexOf("CN=");

		if (i == -1)
			return null;

		dn = dn.substring(i + 3);

		char dncs[] = dn.toCharArray();

		for (i = 0; i < dncs.length && (dncs[i] != ',' || i <= 0 || dncs[i - 1] == '\\'); i++)
			;
		return dn.substring(0, i);
	}

	private static KeyStore getTrustKeyStore() throws Exception {
		KeyStore ks;

		String trustStoreLoc;

		String trustStorePass;

		char buf[];

		if (trustStore != null)
			return trustStore;

		ks = null;

		trustStoreLoc = System.getProperty("javax.net.ssl.trustStore");

		trustStorePass = System.getProperty("javax.net.ssl.trustStorePassword");

		String trustStoreType = System.getProperty("javax.net.ssl.trustStoreType");

		Exception e = new Exception("!!!!");

		if (trustStoreLoc == null)
			throw e;

		ks = KeyStore.getInstance(trustStoreType != null ? trustStoreType : KeyStore.getDefaultType());

		buf = null;

		ks.load(new FileInputStream(trustStoreLoc),
				trustStorePass != null ? (buf = trustStorePass.toCharArray()) : null);

		if (buf != null)
			Arrays.fill(buf, '\0');

		if (buf != null) {
			Arrays.fill(buf, '\0');
			trustStore = ks;
		} else
			return trustStore;

		final class some {
			public InputStream getResourceAsStream(String value) {
				return this.getClass().getClassLoader().getResourceAsStream(value);
			}
		}

		some s = new some();

		InputStream is = s.getResourceAsStream("cis_jdbc_truststore_strong.jks");

		ks = KeyStore.getInstance(KeyStore.getDefaultType());

		ks.load(is, "changeit".toCharArray());

		is = s.getResourceAsStream("cis_jdbc_truststore.jks");

		KeyStore weak = KeyStore.getInstance(KeyStore.getDefaultType());

		weak.load(is, "changeit".toCharArray());

		Enumeration alias = weak.aliases();

		do {
			if (!alias.hasMoreElements())
				break;
			String ali = (String) alias.nextElement();
			if (!ks.containsAlias(ali))
				ks.setCertificateEntry(ali, weak.getCertificate(ali));
		} while (true);

		trustStore = ks;

		return trustStore;
	}

	private static KeyStore trustStore;
	
	
	public static void main(String[] args) { 
		
		//generateSecurityKey()
		
		String sec = "";
		
		try {
			
			for(int i=0;i<1000;i++)
			{
				sec = Bau.to_hex_str_from_byte_array(Encryption.generateSecurityKey());
		
				gl.sfn("%s",sec);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	
		
	}
}
