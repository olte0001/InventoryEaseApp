--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: company1; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA company1;


ALTER SCHEMA company1 OWNER TO postgres;

--
-- Name: company2; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA company2;


ALTER SCHEMA company2 OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: alert; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.alert (
    alert_id integer NOT NULL,
    item_sn character varying(100),
    has_expired boolean,
    low_stock boolean,
    alert_date timestamp without time zone
);


ALTER TABLE company1.alert OWNER TO postgres;

--
-- Name: alert_alert_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.alert_alert_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.alert_alert_id_seq OWNER TO postgres;

--
-- Name: alert_alert_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.alert_alert_id_seq OWNED BY company1.alert.alert_id;


--
-- Name: consumeditem; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.consumeditem (
    consumed_item_id character varying(100) NOT NULL,
    received_date timestamp without time zone,
    expiration_date timestamp without time zone,
    locator_id integer,
    supplier_id integer,
    product_id integer
);


ALTER TABLE company1.consumeditem OWNER TO postgres;

--
-- Name: item; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.item (
    item_id character varying(100) NOT NULL,
    item_quantity integer,
    received_date timestamp without time zone,
    expiration_date timestamp without time zone,
    locator_id integer,
    supplier_id integer,
    product_id integer
);


ALTER TABLE company1.item OWNER TO postgres;

--
-- Name: location; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.location (
    location_id integer NOT NULL,
    location_name character varying(100)
);


ALTER TABLE company1.location OWNER TO postgres;

--
-- Name: location_location_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.location_location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.location_location_id_seq OWNER TO postgres;

--
-- Name: location_location_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.location_location_id_seq OWNED BY company1.location.location_id;


--
-- Name: locator; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.locator (
    locator_id integer NOT NULL,
    storage1 character varying(100),
    storage2 character varying(100),
    location_id integer
);


ALTER TABLE company1.locator OWNER TO postgres;

--
-- Name: locator_locator_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.locator_locator_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.locator_locator_id_seq OWNER TO postgres;

--
-- Name: locator_locator_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.locator_locator_id_seq OWNED BY company1.locator.locator_id;


--
-- Name: product; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.product (
    product_id integer NOT NULL,
    product_name character varying(100),
    can_expire boolean,
    total_quantity integer,
    threshold_min integer
);


ALTER TABLE company1.product OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.product_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.product_product_id_seq OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.product_product_id_seq OWNED BY company1.product.product_id;


--
-- Name: product_supplier; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.product_supplier (
    product_id integer NOT NULL,
    supplier_id integer NOT NULL
);


ALTER TABLE company1.product_supplier OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.roles (
    role_id integer NOT NULL,
    role_name character varying(45)
);


ALTER TABLE company1.roles OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.roles_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.roles_role_id_seq OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.roles_role_id_seq OWNED BY company1.roles.role_id;


--
-- Name: supplier; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.supplier (
    supplier_id integer NOT NULL,
    supplier_name character varying(100),
    address character varying(255),
    phone_no integer
);


ALTER TABLE company1.supplier OWNER TO postgres;

--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.supplier_supplier_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.supplier_supplier_id_seq OWNER TO postgres;

--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.supplier_supplier_id_seq OWNED BY company1.supplier.supplier_id;


--
-- Name: transaction; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.transaction (
    trans_id integer NOT NULL,
    trans_type character varying(45),
    trans_quantity integer,
    item_sn character varying(100),
    trans_date timestamp without time zone,
    comments character varying(255),
    supplier_id integer,
    product_id integer,
    locator_id integer
);


ALTER TABLE company1.transaction OWNER TO postgres;

--
-- Name: transaction_trans_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.transaction_trans_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.transaction_trans_id_seq OWNER TO postgres;

--
-- Name: transaction_trans_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.transaction_trans_id_seq OWNED BY company1.transaction.trans_id;


--
-- Name: users; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.users (
    user_id integer NOT NULL,
    role_id integer,
    first_name character varying(45),
    last_name character varying(45),
    password character varying(100)
);


ALTER TABLE company1.users OWNER TO postgres;

--
-- Name: users_location; Type: TABLE; Schema: company1; Owner: postgres
--

CREATE TABLE company1.users_location (
    user_id integer NOT NULL,
    location_id integer NOT NULL
);


ALTER TABLE company1.users_location OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: company1; Owner: postgres
--

CREATE SEQUENCE company1.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company1.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: company1; Owner: postgres
--

ALTER SEQUENCE company1.users_user_id_seq OWNED BY company1.users.user_id;


--
-- Name: alert; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.alert (
    alert_id integer NOT NULL,
    item_sn character varying(100),
    has_expired boolean,
    low_stock boolean,
    alert_date timestamp without time zone
);


ALTER TABLE company2.alert OWNER TO postgres;

--
-- Name: alert_alert_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.alert_alert_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.alert_alert_id_seq OWNER TO postgres;

--
-- Name: alert_alert_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.alert_alert_id_seq OWNED BY company2.alert.alert_id;


--
-- Name: consumeditem; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.consumeditem (
    consumed_item_id character varying(100) NOT NULL,
    received_date timestamp without time zone,
    expiration_date timestamp without time zone,
    locator_id integer,
    supplier_id integer,
    product_id integer
);


ALTER TABLE company2.consumeditem OWNER TO postgres;

--
-- Name: item; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.item (
    item_id character varying(100) NOT NULL,
    item_quantity integer,
    received_date timestamp without time zone,
    expiration_date timestamp without time zone,
    locator_id integer,
    supplier_id integer,
    product_id integer
);


ALTER TABLE company2.item OWNER TO postgres;

--
-- Name: location; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.location (
    location_id integer NOT NULL,
    location_name character varying(100)
);


ALTER TABLE company2.location OWNER TO postgres;

--
-- Name: location_location_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.location_location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.location_location_id_seq OWNER TO postgres;

--
-- Name: location_location_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.location_location_id_seq OWNED BY company2.location.location_id;


--
-- Name: locator; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.locator (
    locator_id integer NOT NULL,
    storage1 character varying(100),
    storage2 character varying(100),
    location_id integer
);


ALTER TABLE company2.locator OWNER TO postgres;

--
-- Name: locator_locator_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.locator_locator_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.locator_locator_id_seq OWNER TO postgres;

--
-- Name: locator_locator_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.locator_locator_id_seq OWNED BY company2.locator.locator_id;


--
-- Name: product; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.product (
    product_id integer NOT NULL,
    product_name character varying(100),
    can_expire boolean,
    total_quantity integer,
    threshold_min integer
);


ALTER TABLE company2.product OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.product_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.product_product_id_seq OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.product_product_id_seq OWNED BY company2.product.product_id;


--
-- Name: product_supplier; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.product_supplier (
    product_id integer NOT NULL,
    supplier_id integer NOT NULL
);


ALTER TABLE company2.product_supplier OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.roles (
    role_id integer NOT NULL,
    role_name character varying(45)
);


ALTER TABLE company2.roles OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.roles_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.roles_role_id_seq OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.roles_role_id_seq OWNED BY company2.roles.role_id;


--
-- Name: supplier; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.supplier (
    supplier_id integer NOT NULL,
    supplier_name character varying(100),
    address character varying(255),
    phone_no integer
);


ALTER TABLE company2.supplier OWNER TO postgres;

--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.supplier_supplier_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.supplier_supplier_id_seq OWNER TO postgres;

--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.supplier_supplier_id_seq OWNED BY company2.supplier.supplier_id;


--
-- Name: transaction; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.transaction (
    trans_id integer NOT NULL,
    trans_type character varying(45),
    trans_quantity integer,
    item_sn character varying(100),
    trans_date timestamp without time zone,
    comments character varying(255),
    supplier_id integer,
    product_id integer,
    locator_id integer
);


ALTER TABLE company2.transaction OWNER TO postgres;

--
-- Name: transaction_trans_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.transaction_trans_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.transaction_trans_id_seq OWNER TO postgres;

--
-- Name: transaction_trans_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.transaction_trans_id_seq OWNED BY company2.transaction.trans_id;


--
-- Name: users; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.users (
    user_id integer NOT NULL,
    first_name character varying(45),
    last_name character varying(45),
    password character varying(100),
    role_id integer
);


ALTER TABLE company2.users OWNER TO postgres;

--
-- Name: users_location; Type: TABLE; Schema: company2; Owner: postgres
--

CREATE TABLE company2.users_location (
    user_id integer NOT NULL,
    location_id integer NOT NULL
);


ALTER TABLE company2.users_location OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: company2; Owner: postgres
--

CREATE SEQUENCE company2.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE company2.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: company2; Owner: postgres
--

ALTER SEQUENCE company2.users_user_id_seq OWNED BY company2.users.user_id;


--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    company_id bigint NOT NULL,
    company_name character varying(255) NOT NULL,
    schema_name text NOT NULL
);


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: COMPANY_company_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."COMPANY_company_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."COMPANY_company_id_seq" OWNER TO postgres;

--
-- Name: COMPANY_company_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."COMPANY_company_id_seq" OWNED BY public.company.company_id;


--
-- Name: alert alert_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.alert ALTER COLUMN alert_id SET DEFAULT nextval('company1.alert_alert_id_seq'::regclass);


--
-- Name: location location_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.location ALTER COLUMN location_id SET DEFAULT nextval('company1.location_location_id_seq'::regclass);


--
-- Name: locator locator_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.locator ALTER COLUMN locator_id SET DEFAULT nextval('company1.locator_locator_id_seq'::regclass);


--
-- Name: product product_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.product ALTER COLUMN product_id SET DEFAULT nextval('company1.product_product_id_seq'::regclass);


--
-- Name: roles role_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.roles ALTER COLUMN role_id SET DEFAULT nextval('company1.roles_role_id_seq'::regclass);


--
-- Name: supplier supplier_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.supplier ALTER COLUMN supplier_id SET DEFAULT nextval('company1.supplier_supplier_id_seq'::regclass);


--
-- Name: transaction trans_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.transaction ALTER COLUMN trans_id SET DEFAULT nextval('company1.transaction_trans_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users ALTER COLUMN user_id SET DEFAULT nextval('company1.users_user_id_seq'::regclass);


--
-- Name: alert alert_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.alert ALTER COLUMN alert_id SET DEFAULT nextval('company2.alert_alert_id_seq'::regclass);


--
-- Name: location location_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.location ALTER COLUMN location_id SET DEFAULT nextval('company2.location_location_id_seq'::regclass);


--
-- Name: locator locator_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.locator ALTER COLUMN locator_id SET DEFAULT nextval('company2.locator_locator_id_seq'::regclass);


--
-- Name: product product_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.product ALTER COLUMN product_id SET DEFAULT nextval('company2.product_product_id_seq'::regclass);


--
-- Name: roles role_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.roles ALTER COLUMN role_id SET DEFAULT nextval('company2.roles_role_id_seq'::regclass);


--
-- Name: supplier supplier_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.supplier ALTER COLUMN supplier_id SET DEFAULT nextval('company2.supplier_supplier_id_seq'::regclass);


--
-- Name: transaction trans_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.transaction ALTER COLUMN trans_id SET DEFAULT nextval('company2.transaction_trans_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users ALTER COLUMN user_id SET DEFAULT nextval('company2.users_user_id_seq'::regclass);


--
-- Name: company company_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company ALTER COLUMN company_id SET DEFAULT nextval('public."COMPANY_company_id_seq"'::regclass);


--
-- Data for Name: alert; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.alert (alert_id, item_sn, has_expired, low_stock, alert_date) FROM stdin;
1	ITEM001	f	t	2025-04-15 15:34:15.257454
\.


--
-- Data for Name: consumeditem; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.consumeditem (consumed_item_id, received_date, expiration_date, locator_id, supplier_id, product_id) FROM stdin;
CITEM001	2024-03-20 00:00:00	2024-05-21 00:00:00	2	2	2
CITEM002	2024-03-17 00:00:00	2024-06-03 00:00:00	2	2	2
\.


--
-- Data for Name: item; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.item (item_id, item_quantity, received_date, expiration_date, locator_id, supplier_id, product_id) FROM stdin;
ITEM001	41	2024-03-07 00:00:00	\N	1	1	2
ITEM002	23	2024-03-25 00:00:00	2024-05-28 00:00:00	1	2	1
ITEM003	83	2024-03-12 00:00:00	2024-06-25 00:00:00	1	1	1
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.location (location_id, location_name) FROM stdin;
1	Main Warehouse
2	Secondary Storage
3	Cold Room
\.


--
-- Data for Name: locator; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.locator (locator_id, storage1, storage2, location_id) FROM stdin;
1	Shelf A	Row 1	1
2	Shelf B	Row 2	2
3	Freezer 1	Cold	3
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.product (product_id, product_name, can_expire, total_quantity, threshold_min) FROM stdin;
1	Milk	t	100	20
2	Rice	f	500	100
\.


--
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.product_supplier (product_id, supplier_id) FROM stdin;
1	1
1	2
2	2
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.roles (role_id, role_name) FROM stdin;
1	MANAGER
2	HANDLER
\.


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.supplier (supplier_id, supplier_name, address, phone_no) FROM stdin;
1	SupplierA	123 Street	123456789
2	SupplierB	456 Avenue	987654321
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.transaction (trans_id, trans_type, trans_quantity, item_sn, trans_date, comments, supplier_id, product_id, locator_id) FROM stdin;
1	IN	20	ITEM001	2025-04-15 15:34:15.257454	Initial stock	1	1	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.users (user_id, role_id, first_name, last_name, password) FROM stdin;
1	1	John	Doe	$2b$12$OPTKjBBAdHzZ2PvbR4EAy.LS8KyNJLSu32RrzkCb8SH0BT/3SrOVq
2	1	Alice	Wong	$2b$12$LVtMWa.tmtwctwA4EnuXx./yOxYR4BC9tkvYD4TyKtBqyHoyPy/SO
3	2	Carol	Lee	$2b$12$TCFar8VA765m.Xmv1cvWDeOR1roNI277v.MCxsM6SGhFOULqre1r.
4	2	Eve	Kim	$2b$12$sXElKWcEZ.1F.Tr71v6FuO7dbKD2KA2gpeMPuPvsR6NILT.CMsEaK
\.


--
-- Data for Name: users_location; Type: TABLE DATA; Schema: company1; Owner: postgres
--

COPY company1.users_location (user_id, location_id) FROM stdin;
1	1
1	2
2	2
\.


--
-- Data for Name: alert; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.alert (alert_id, item_sn, has_expired, low_stock, alert_date) FROM stdin;
\.


--
-- Data for Name: consumeditem; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.consumeditem (consumed_item_id, received_date, expiration_date, locator_id, supplier_id, product_id) FROM stdin;
\.


--
-- Data for Name: item; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.item (item_id, item_quantity, received_date, expiration_date, locator_id, supplier_id, product_id) FROM stdin;
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.location (location_id, location_name) FROM stdin;
\.


--
-- Data for Name: locator; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.locator (locator_id, storage1, storage2, location_id) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.product (product_id, product_name, can_expire, total_quantity, threshold_min) FROM stdin;
\.


--
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.product_supplier (product_id, supplier_id) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.roles (role_id, role_name) FROM stdin;
1	MANAGER
2	HANDLER
\.


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.supplier (supplier_id, supplier_name, address, phone_no) FROM stdin;
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.transaction (trans_id, trans_type, trans_quantity, item_sn, trans_date, comments, supplier_id, product_id, locator_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.users (user_id, first_name, last_name, password, role_id) FROM stdin;
\.


--
-- Data for Name: users_location; Type: TABLE DATA; Schema: company2; Owner: postgres
--

COPY company2.users_location (user_id, location_id) FROM stdin;
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company (company_id, company_name, schema_name) FROM stdin;
5193	Mayer&Son	company1
374	Telephonia	company2
\.


--
-- Name: alert_alert_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.alert_alert_id_seq', 1, true);


--
-- Name: location_location_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.location_location_id_seq', 12, true);


--
-- Name: locator_locator_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.locator_locator_id_seq', 3, true);


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.product_product_id_seq', 4, true);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.roles_role_id_seq', 1, false);


--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.supplier_supplier_id_seq', 6, true);


--
-- Name: transaction_trans_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.transaction_trans_id_seq', 1, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: company1; Owner: postgres
--

SELECT pg_catalog.setval('company1.users_user_id_seq', 1, false);


--
-- Name: alert_alert_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.alert_alert_id_seq', 1, false);


--
-- Name: location_location_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.location_location_id_seq', 1, false);


--
-- Name: locator_locator_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.locator_locator_id_seq', 1, false);


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.product_product_id_seq', 1, false);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.roles_role_id_seq', 1, false);


--
-- Name: supplier_supplier_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.supplier_supplier_id_seq', 1, false);


--
-- Name: transaction_trans_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.transaction_trans_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: company2; Owner: postgres
--

SELECT pg_catalog.setval('company2.users_user_id_seq', 1, false);


--
-- Name: COMPANY_company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."COMPANY_company_id_seq"', 1, false);


--
-- Name: alert alert_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.alert
    ADD CONSTRAINT alert_pkey PRIMARY KEY (alert_id);


--
-- Name: consumeditem consumeditem_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.consumeditem
    ADD CONSTRAINT consumeditem_pkey PRIMARY KEY (consumed_item_id);


--
-- Name: item item_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_id);


--
-- Name: locator locator_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.locator
    ADD CONSTRAINT locator_pkey PRIMARY KEY (locator_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: product_supplier product_supplier_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.product_supplier
    ADD CONSTRAINT product_supplier_pkey PRIMARY KEY (product_id, supplier_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (supplier_id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (trans_id);


--
-- Name: users_location users_location_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users_location
    ADD CONSTRAINT users_location_pkey PRIMARY KEY (user_id, location_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: alert alert_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.alert
    ADD CONSTRAINT alert_pkey PRIMARY KEY (alert_id);


--
-- Name: consumeditem consumeditem_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.consumeditem
    ADD CONSTRAINT consumeditem_pkey PRIMARY KEY (consumed_item_id);


--
-- Name: item item_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_id);


--
-- Name: locator locator_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.locator
    ADD CONSTRAINT locator_pkey PRIMARY KEY (locator_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: product_supplier product_supplier_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.product_supplier
    ADD CONSTRAINT product_supplier_pkey PRIMARY KEY (product_id, supplier_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (supplier_id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (trans_id);


--
-- Name: users_location users_location_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users_location
    ADD CONSTRAINT users_location_pkey PRIMARY KEY (user_id, location_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: company COMPANY_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT "COMPANY_pkey" PRIMARY KEY (company_id);


--
-- Name: consumeditem consumeditem_locator_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.consumeditem
    ADD CONSTRAINT consumeditem_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company1.locator(locator_id);


--
-- Name: consumeditem consumeditem_product_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.consumeditem
    ADD CONSTRAINT consumeditem_product_id_fkey FOREIGN KEY (product_id) REFERENCES company1.product(product_id);


--
-- Name: consumeditem consumeditem_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.consumeditem
    ADD CONSTRAINT consumeditem_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company1.supplier(supplier_id);


--
-- Name: item item_locator_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.item
    ADD CONSTRAINT item_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company1.locator(locator_id);


--
-- Name: item item_product_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.item
    ADD CONSTRAINT item_product_id_fkey FOREIGN KEY (product_id) REFERENCES company1.product(product_id);


--
-- Name: item item_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.item
    ADD CONSTRAINT item_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company1.supplier(supplier_id);


--
-- Name: locator locator_location_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.locator
    ADD CONSTRAINT locator_location_id_fkey FOREIGN KEY (location_id) REFERENCES company1.location(location_id);


--
-- Name: product_supplier product_supplier_product_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.product_supplier
    ADD CONSTRAINT product_supplier_product_id_fkey FOREIGN KEY (product_id) REFERENCES company1.product(product_id);


--
-- Name: product_supplier product_supplier_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.product_supplier
    ADD CONSTRAINT product_supplier_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company1.supplier(supplier_id);


--
-- Name: transaction transaction_locator_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.transaction
    ADD CONSTRAINT transaction_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company1.locator(locator_id);


--
-- Name: transaction transaction_product_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.transaction
    ADD CONSTRAINT transaction_product_id_fkey FOREIGN KEY (product_id) REFERENCES company1.product(product_id);


--
-- Name: transaction transaction_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.transaction
    ADD CONSTRAINT transaction_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company1.supplier(supplier_id);


--
-- Name: users_location users_location_location_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users_location
    ADD CONSTRAINT users_location_location_id_fkey FOREIGN KEY (location_id) REFERENCES company1.location(location_id);


--
-- Name: users_location users_location_user_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users_location
    ADD CONSTRAINT users_location_user_id_fkey FOREIGN KEY (user_id) REFERENCES company1.users(user_id);


--
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: company1; Owner: postgres
--

ALTER TABLE ONLY company1.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES company1.roles(role_id);


--
-- Name: consumeditem consumeditem_locator_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.consumeditem
    ADD CONSTRAINT consumeditem_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company2.locator(locator_id);


--
-- Name: consumeditem consumeditem_product_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.consumeditem
    ADD CONSTRAINT consumeditem_product_id_fkey FOREIGN KEY (product_id) REFERENCES company2.product(product_id);


--
-- Name: consumeditem consumeditem_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.consumeditem
    ADD CONSTRAINT consumeditem_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company2.supplier(supplier_id);


--
-- Name: item item_locator_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.item
    ADD CONSTRAINT item_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company2.locator(locator_id);


--
-- Name: item item_product_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.item
    ADD CONSTRAINT item_product_id_fkey FOREIGN KEY (product_id) REFERENCES company2.product(product_id);


--
-- Name: item item_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.item
    ADD CONSTRAINT item_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company2.supplier(supplier_id);


--
-- Name: locator locator_location_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.locator
    ADD CONSTRAINT locator_location_id_fkey FOREIGN KEY (location_id) REFERENCES company2.location(location_id);


--
-- Name: product_supplier product_supplier_product_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.product_supplier
    ADD CONSTRAINT product_supplier_product_id_fkey FOREIGN KEY (product_id) REFERENCES company2.product(product_id);


--
-- Name: product_supplier product_supplier_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.product_supplier
    ADD CONSTRAINT product_supplier_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company2.supplier(supplier_id);


--
-- Name: transaction transaction_locator_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.transaction
    ADD CONSTRAINT transaction_locator_id_fkey FOREIGN KEY (locator_id) REFERENCES company2.locator(locator_id);


--
-- Name: transaction transaction_product_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.transaction
    ADD CONSTRAINT transaction_product_id_fkey FOREIGN KEY (product_id) REFERENCES company2.product(product_id);


--
-- Name: transaction transaction_supplier_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.transaction
    ADD CONSTRAINT transaction_supplier_id_fkey FOREIGN KEY (supplier_id) REFERENCES company2.supplier(supplier_id);


--
-- Name: users_location users_location_location_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users_location
    ADD CONSTRAINT users_location_location_id_fkey FOREIGN KEY (location_id) REFERENCES company2.location(location_id);


--
-- Name: users_location users_location_user_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users_location
    ADD CONSTRAINT users_location_user_id_fkey FOREIGN KEY (user_id) REFERENCES company2.users(user_id);


--
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: company2; Owner: postgres
--

ALTER TABLE ONLY company2.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES company2.roles(role_id);


--
-- PostgreSQL database dump complete
--

