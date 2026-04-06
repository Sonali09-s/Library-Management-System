//package com.expense.service.serviceImpl;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.GmailScopes;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//import java.util.List;
//
//public class GmailOAuthSetup {
//	 private static final String APPLICATION_NAME = "Gmail API Java OAuth Setup";
//	    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//	    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
//	    private static final String CREDENTIALS_FILE_PATH = "credentials.json"; // your downloaded file
//	    private static final String TOKENS_DIRECTORY_PATH = "tokens";
//
//	    public static void main(String[] args) throws IOException, GeneralSecurityException {
//	        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//
//	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
//	                JSON_FACTORY,
//	                new FileReader(CREDENTIALS_FILE_PATH)
//	        );
//
//	        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//	                HTTP_TRANSPORT,
//	                JSON_FACTORY,
//	                clientSecrets,
//	                SCOPES
//	        )
//	        .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
//	        .setAccessType("offline")
//	        .build();
//
//	        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//
//	        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//
//	        System.out.println("Access Token: " + credential.getAccessToken());
//	        System.out.println("Refresh Token: " + credential.getRefreshToken());
//
//	        // Optional: verify Gmail API access
//	        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
//	                .setApplicationName(APPLICATION_NAME)
//	                .build();
//
//	        System.out.println("OAuth setup complete. Gmail API is ready.");
//	    }
//	}