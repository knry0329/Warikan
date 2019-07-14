--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9 (Ubuntu 10.9-1.pgdg16.04+1)
-- Dumped by pg_dump version 11.4

\c user1;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: jkanqmkgmmbddv
--


ALTER SCHEMA public OWNER TO user1;
-- ALTER SCHEMA public OWNER TO jkanqmkgmmbddv;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: jkanqmkgmmbddv
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: m_user; Type: TABLE; Schema: public; Owner: jkanqmkgmmbddv
--

CREATE TABLE public.m_user (
    user_id character varying(20) NOT NULL,
    password character varying(128) NOT NULL
);


ALTER TABLE public.m_user OWNER TO user1;
-- ALTER TABLE public.m_user OWNER TO jkanqmkgmmbddv;

--
-- Name: sample; Type: TABLE; Schema: public; Owner: jkanqmkgmmbddv
--

CREATE TABLE public.sample (
    crttimestamp timestamp with time zone,
    crtuser character varying(20),
    udttimestamp timestamp with time zone,
    udtuser character varying(20),
    id character varying(10) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.sample OWNER TO user1;
--ALTER TABLE public.sample OWNER TO jkanqmkgmmbddv;

--
-- Name: t_expence; Type: TABLE; Schema: public; Owner: jkanqmkgmmbddv
--

CREATE TABLE public.t_expence (
    user_id character varying(20) NOT NULL,
    row_id integer NOT NULL,
    pay_person character varying(200),
    purpose character varying(200),
    expence integer
);


ALTER TABLE public.t_expence OWNER TO user1;
-- ALTER TABLE public.t_expence OWNER TO jkanqmkgmmbddv;

--
-- Name: m_user M_USER_pkey; Type: CONSTRAINT; Schema: public; Owner: jkanqmkgmmbddv
--

ALTER TABLE ONLY public.m_user
    ADD CONSTRAINT "M_USER_pkey" PRIMARY KEY (user_id);


--
-- Name: t_expence T_EXPENCE_pkey; Type: CONSTRAINT; Schema: public; Owner: jkanqmkgmmbddv
--

ALTER TABLE ONLY public.t_expence
    ADD CONSTRAINT "T_EXPENCE_pkey" PRIMARY KEY (user_id, row_id);


--
-- Name: sample sample_id_pkey; Type: CONSTRAINT; Schema: public; Owner: jkanqmkgmmbddv
--

ALTER TABLE ONLY public.sample
    ADD CONSTRAINT sample_id_pkey PRIMARY KEY (id);


--
-- Name: t_expence t_expence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jkanqmkgmmbddv
--

ALTER TABLE ONLY public.t_expence
    ADD CONSTRAINT t_expence_fkey FOREIGN KEY (user_id) REFERENCES public.m_user(user_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: jkanqmkgmmbddv
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO user1;
--GRANT ALL ON SCHEMA public TO jkanqmkgmmbddv;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

-- GRANT ALL ON LANGUAGE plpgsql TO jkanqmkgmmbddv;
GRANT ALL ON LANGUAGE plpgsql TO user1;


--
-- PostgreSQL database dump complete
--

